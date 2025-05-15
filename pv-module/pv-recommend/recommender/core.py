import logging
import time
from decimal import Decimal
from collections import defaultdict
from typing import Dict, List, Optional
import numpy as np
from sklearn.metrics.pairwise import cosine_similarity

logger = logging.getLogger('MovieRecommenderAPI')


class MovieRecommender:
    def __init__(self, db_conn):
        """初始化推荐系统"""
        self.conn = db_conn
        self.min_similar_items = 5
        self.max_similar_items = 100
        self.hybrid_cf_weight = 0.7
        self.hybrid_cs_weight = 0.3
        self.movie_cache_size = 10000

        # 数据缓存
        self.movie_info = {}
        self.user_ratings = {}
        self.item_similarity = None
        self.last_update_time = 0

        # 初始化数据
        self._load_movie_cache()

    def _execute_query(self, query: str, params=None, fetchall=True):
        """执行SQL查询"""
        try:
            with self.conn.cursor() as cursor:
                cursor.execute(query, params or ())
                return cursor.fetchall() if fetchall else cursor.fetchone()
        except Exception as e:
            logger.error(f"查询失败: {str(e)}")
            raise

    def _load_movie_cache(self):
        """加载电影缓存"""
        logger.info("正在加载电影缓存...")
        query = """
            SELECT movie_id, name, genres, douban_score, douban_votes, 
                   cover, directors, actors, year, mins, storyline
            FROM pv_movie
            WHERE douban_score IS NOT NULL
            ORDER BY douban_votes DESC
            LIMIT %s
        """
        try:
            movies = self._execute_query(query, (self.movie_cache_size,))
            if not movies:
                logger.error("数据库中未找到电影数据!")
                return

            self.movie_info = {
                m['movie_id']: {
                    **m,
                    'douban_score': float(m['douban_score']) if m['douban_score'] is not None else 0.0
                }
                for m in movies
            }
            logger.info(f"成功加载 {len(self.movie_info)} 部电影到缓存")
        except Exception as e:
            logger.error(f"加载电影缓存失败: {str(e)}")
            self.movie_info = {}

    def _get_user_ratings(self, user_id: str) -> Dict[str, float]:
        """获取用户评分数据"""
        logger.info(f"正在获取用户 {user_id} 的评分数据")
        query = """
            SELECT movie_id, rating 
            FROM pv_rating 
            WHERE user_id = %s
        """
        try:
            ratings = self._execute_query(query, (user_id,))
            if not ratings:
                logger.info(f"用户 {user_id} 没有评分记录")
                return {}

            user_ratings = {
                r['movie_id']: float(r['rating']) if isinstance(r['rating'], Decimal) else r['rating']
                for r in ratings
            }
            logger.info(f"找到 {len(user_ratings)} 条用户 {user_id} 的评分")
            return user_ratings
        except Exception as e:
            logger.error(f"获取用户 {user_id} 评分失败: {str(e)}")
            return {}

    def _get_user_liked_genres(self, user_id: str) -> List[str]:
        """获取用户喜欢的电影类型"""
        logger.info(f"正在获取用户 {user_id} 喜欢的电影类型")
        query = """
            SELECT g.genre_name 
            FROM pv_user_like ul 
            JOIN pv_genre g ON ul.genre_id = g.id 
            WHERE ul.user_id = %s
        """
        try:
            genres = self._execute_query(query, (user_id,))
            if not genres:
                logger.info(f"用户 {user_id} 没有设置喜欢的电影类型")
                return []

            genre_list = [g['genre_name'] for g in genres]
            logger.info(f"找到用户 {user_id} 喜欢的 {len(genre_list)} 种电影类型: {genre_list}")
            return genre_list
        except Exception as e:
            logger.error(f"获取用户 {user_id} 喜欢的电影类型失败: {str(e)}")
            return []

    def _get_popular_movies(self, top_n: int) -> List[Dict]:
        """获取热门电影"""
        logger.info(f"正在获取 {top_n} 部热门电影")
        try:
            # 先从缓存中获取
            if self.movie_info:
                movies = sorted(
                    self.movie_info.values(),
                    key=lambda x: (x['douban_score'], x['douban_votes']),
                    reverse=True
                )
                if movies:
                    logger.info(f"从缓存返回 {min(top_n, len(movies))} 部热门电影")
                    return movies[:top_n]

            # 如果缓存中没有，从数据库获取
            query = """
                SELECT movie_id, name, genres, douban_score, douban_votes, 
                       cover, directors, actors, year, mins, storyline
                FROM pv_movie
                WHERE douban_score IS NOT NULL
                ORDER BY douban_score DESC, douban_votes DESC
                LIMIT %s
            """
            movies = self._execute_query(query, (top_n,))
            if not movies:
                logger.error("数据库中未找到热门电影!")
                return []

            logger.info(f"从数据库找到 {len(movies)} 部热门电影")
            return [{
                'movie_id': m['movie_id'],
                'name': m['name'],
                'year': m['year'],
                'genres': m['genres'],
                'douban_score': float(m['douban_score']) if m['douban_score'] is not None else 0.0,
                'cover': m['cover'],
                'directors': m['directors'],
                'actors': m['actors'],
                'mins': m['mins'],
                'storyline': m['storyline']
            } for m in movies]
        except Exception as e:
            logger.error(f"获取热门电影失败: {str(e)}")
            return []

    def _get_cold_start_recommendations(self, user_id: str, top_n: int) -> List[Dict]:
        """获取冷启动推荐"""
        logger.info(f"正在为用户 {user_id} 生成冷启动推荐，数量={top_n}")
        try:
            # 获取用户喜欢的类型
            liked_genres = self._get_user_liked_genres(user_id)

            if not liked_genres:
                logger.info(f"用户 {user_id} 没有设置喜欢的电影类型，返回热门电影")
                return self._get_popular_movies(top_n)

            # 构建类型查询条件
            genre_conditions = " OR ".join(["FIND_IN_SET(%s, genres) > 0" for _ in liked_genres])

            # 查询这些类型的高分电影
            query = f"""
                SELECT movie_id, name, genres, douban_score, douban_votes, 
                       cover, directors, actors, year, mins, storyline
                FROM pv_movie
                WHERE douban_score IS NOT NULL
                AND ({genre_conditions})
                ORDER BY douban_score DESC, douban_votes DESC
                LIMIT %s
            """

            # 获取足够多的候选结果
            params = liked_genres + [top_n * 3]
            movies = self._execute_query(query, params)

            if not movies:
                logger.info(f"没有找到符合用户喜好的电影，返回热门电影")
                return self._get_popular_movies(top_n)

            # 转换为标准格式
            recommendations = [{
                'movie_id': m['movie_id'],
                'name': m['name'],
                'year': m['year'],
                'genres': m['genres'],
                'douban_score': float(m['douban_score']) if m['douban_score'] is not None else 0.0,
                'cover': m['cover'],
                'directors': m['directors'],
                'actors': m['actors'],
                'mins': m['mins'],
                'storyline': m['storyline']
            } for m in movies]

            return recommendations[:top_n]

        except Exception as e:
            logger.error(f"为用户 {user_id} 生成冷启动推荐失败: {str(e)}")
            return self._get_popular_movies(top_n)[:top_n]

    def _calculate_item_similarity(self):
        """计算物品相似度矩阵"""
        if time.time() - self.last_update_time < 3600 and self.item_similarity is not None:
            return

        logger.info("正在计算物品相似度矩阵...")

        # 获取评分数据构建用户-物品矩阵
        query = """
            SELECT user_id, movie_id, rating
            FROM pv_rating
            WHERE movie_id IN (
                SELECT movie_id FROM pv_movie 
                WHERE douban_score IS NOT NULL
                ORDER BY douban_votes DESC
                LIMIT 5000
            )
            LIMIT 100000
        """

        try:
            ratings = self._execute_query(query)

            if not ratings:
                logger.error("没有找到评分数据用于相似度计算")
                return

            # 构建用户-物品矩阵
            user_item_matrix = defaultdict(dict)
            for r in ratings:
                rating = float(r['rating']) if isinstance(r['rating'], Decimal) else r['rating']
                user_item_matrix[r['user_id']][r['movie_id']] = rating

            # 转换为DataFrame格式
            all_movies = list({m for u in user_item_matrix.values() for m in u})
            user_ids = list(user_item_matrix.keys())

            if not all_movies or not user_ids:
                logger.error("数据不足，无法计算相似度矩阵")
                return

            matrix = np.zeros((len(user_ids), len(all_movies)))
            for i, user_id in enumerate(user_ids):
                for j, movie_id in enumerate(all_movies):
                    matrix[i, j] = user_item_matrix[user_id].get(movie_id, 0)

            # 计算余弦相似度
            similarity = cosine_similarity(matrix.T)
            self.item_similarity = {
                movie_id: {
                    all_movies[j]: similarity[i][j]
                    for j in range(len(all_movies))
                    if i != j and similarity[i][j] > 0
                }
                for i, movie_id in enumerate(all_movies)
            }

            self.last_update_time = time.time()
            logger.info(f"物品相似度矩阵已更新，包含 {len(self.item_similarity)} 个物品")

        except Exception as e:
            logger.error(f"计算物品相似度失败: {str(e)}")
            self.item_similarity = None

    def hybrid_recommendation(self, user_id: str, top_n: int) -> List[Dict]:
        """混合推荐 - 结合协同过滤和冷启动推荐（只返回电影ID）"""
        logger.info(f"正在为用户 {user_id} 生成混合推荐，数量={top_n}")

        try:
            # 获取冷启动推荐候选
            cs_rec = self._get_cold_start_recommendations(user_id, top_n * 3)

            # 获取用户评分数据
            user_ratings = self._get_user_ratings(user_id)

            # 如果没有评分记录，直接返回冷启动推荐（只返回ID）
            if not user_ratings:
                logger.info(f"用户 {user_id} 没有评分记录，返回冷启动推荐")
                return [{'movie_id': movie['movie_id']} for movie in cs_rec[:top_n]]

            # 计算物品相似度
            self._calculate_item_similarity()
            if not self.item_similarity:
                logger.warning("物品相似度矩阵不可用，返回冷启动推荐")
                return [{'movie_id': movie['movie_id']} for movie in cs_rec[:top_n]]

            # 计算推荐分数
            recommendation_scores = defaultdict(float)
            rated_movies = set(user_ratings.keys())

            for movie_id, rating in user_ratings.items():
                if movie_id not in self.item_similarity:
                    continue

                # 获取相似物品
                similar_items = sorted(
                    self.item_similarity[movie_id].items(),
                    key=lambda x: x[1],
                    reverse=True
                )[:self.max_similar_items]

                # 累加相似度分数
                for similar_movie, similarity in similar_items:
                    if similar_movie not in rated_movies:
                        recommendation_scores[similar_movie] += similarity * rating

            # 获取推荐电影候选
            recommended_movies = sorted(
                recommendation_scores.items(),
                key=lambda x: x[1],
                reverse=True
            )[:top_n * 3]

            # 获取电影详情（只保留ID）
            cf_rec = []
            for movie_id, score in recommended_movies:
                if movie_id in self.movie_info:
                    cf_rec.append({'movie_id': movie_id})

            # 合并结果并去重
            recommendations = {}

            # 处理协同过滤推荐结果
            for movie in cf_rec:
                movie_id = movie['movie_id']
                if movie_id not in recommendations:
                    recommendations[movie_id] = {
                        'movie_id': movie_id,
                        'source': 'cf'
                    }

            # 处理冷启动推荐结果
            for movie in cs_rec:
                movie_id = movie['movie_id']
                if movie_id in recommendations:
                    recommendations[movie_id]['source'] = 'hybrid'
                else:
                    recommendations[movie_id] = {
                        'movie_id': movie_id,
                        'source': 'cs'
                    }

            # 按推荐来源排序（CF优先）
            sorted_rec = sorted(
                recommendations.values(),
                key=lambda x: (1 if x['source'] == 'cf' else 0, x['movie_id']),
                reverse=True
            )

            # 返回结果（只包含电影ID）
            results = [{'movie_id': item['movie_id']} for item in sorted_rec[:top_n]]

            logger.info(f"为用户 {user_id} 生成了 {len(results)} 条混合推荐")
            return results

        except Exception as e:
            logger.error(f"为用户 {user_id} 生成混合推荐失败: {str(e)}")
            # 出错时也保持只返回电影ID的格式
            return [{'movie_id': m['movie_id']} for m in self._get_cold_start_recommendations(user_id, top_n)[:top_n]]
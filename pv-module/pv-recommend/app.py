from flask import Flask, request, jsonify, g
import time
from database.connection import DBConnectionPool
from recommender.core import MovieRecommender
from utils.logger import setup_logger
from config.config import ConfigManager

# 初始化日志
logger = setup_logger()

# 创建Flask应用
app = Flask(__name__)


def initialize_configurations():
    """
    初始化应用配置
    返回: (db_config, app_config)
    """
    print("\n=== 应用初始化配置 ===")

    # 1. 获取数据库配置
    print("\n[1/2] 数据库配置")
    db_config = ConfigManager.load_db_config()
    print("\n当前数据库配置:")
    for key, value in db_config.items():
        if key != 'password':
            print(f"{key}: {value}")
        else:
            print(f"{key}: {'*' * len(value)}")

    # 2. 获取应用配置
    print("\n[2/2] 应用运行参数")
    app_config = ConfigManager.load_app_config()

    # 打印最终配置
    print("\n最终配置确认:")
    print(f"数据库主机: {db_config['host']}:{db_config['port']}")
    print(f"应用地址: http://{app_config['host']}:{app_config['port']}")
    print(f"调试模式: {'开启' if app_config['debug'] else '关闭'}")

    return db_config, app_config


# 初始化配置
db_config, app_config = initialize_configurations()

# 创建连接池（传入已获取的配置避免重复提示）
connection_pool = DBConnectionPool(db_config=db_config)


@app.before_request
def before_request():
    """在每个请求前获取数据库连接"""
    g.db_conn = connection_pool.get_connection()


@app.teardown_request
def teardown_request(exception):
    """在每个请求后释放数据库连接"""
    if hasattr(g, 'db_conn'):
        connection_pool.return_connection(g.db_conn)


@app.route('/api/recommend', methods=['GET'])
def get_recommendations():
    """获取推荐结果接口（只返回电影ID）"""
    try:
        user_id = request.args.get('user_id')
        top_n = request.args.get('top_n', default=10, type=int)

        if not user_id:
            return jsonify({
                'status': 'error',
                'message': '缺少必要参数: user_id'
            }), 400

        # 验证top_n参数
        try:
            top_n = int(top_n)
            if top_n <= 0:
                raise ValueError("top_n 必须是正整数")
            if top_n > 100:
                top_n = 100
                logger.info(f"请求的 top_n 超过最大限制，将使用 {top_n} 代替")
        except (ValueError, TypeError):
            return jsonify({
                'status': 'error',
                'message': '参数 "top_n" 必须是正整数'
            }), 400

        # 创建推荐器实例
        recommender = MovieRecommender(g.db_conn)

        # 获取推荐结果
        recommendations = recommender.hybrid_recommendation(user_id, top_n)

        # 确保总有推荐结果
        if not recommendations:
            logger.warning(f"没有为用户 {user_id} 生成推荐，返回热门电影")
            recommendations = [{'movie_id': m['movie_id']} for m in recommender._get_popular_movies(top_n)]

        return jsonify({
            'status': 'success',
            'data': {
                'user_id': user_id,
                'count': len(recommendations),
                'movie_ids': [r['movie_id'] for r in recommendations]
            }
        })

    except Exception as e:
        logger.error(f"推荐请求处理失败: {str(e)}")
        return jsonify({
            'status': 'error',
            'message': '服务器内部错误'
        }), 500


@app.route('/api/healthy', methods=['GET'])
def health_check():
    """健康检查接口"""
    try:
        # 测试数据库连接
        conn = connection_pool.get_connection()
        with conn.cursor() as cursor:
            cursor.execute("SELECT 1")

        # 测试电影缓存
        recommender = MovieRecommender(conn)
        popular_movies = recommender._get_popular_movies(1)

        connection_pool.return_connection(conn)

        return jsonify({
            'status': 'healthy',
            'database': 'connected',
            'movie_cache': 'loaded' if popular_movies else 'empty',
            'timestamp': time.time()
        })
    except Exception as e:
        logger.error(f"健康检查失败: {str(e)}")
        return jsonify({
            'status': 'unhealthy',
            'database': 'disconnected',
            'error': str(e),
            'timestamp': time.time()
        }), 500


if __name__ == '__main__':
    # 使用配置启动应用
    print("\n=== 启动应用 ===")
    print(f"服务地址: http://{app_config['host']}:{app_config['port']}")
    print(f"调试模式: {'开启' if app_config['debug'] else '关闭'}")

    app.run(
        host=app_config['host'],
        port=app_config['port'],
        debug=app_config['debug']
    )
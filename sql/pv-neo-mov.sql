-- ----------------------------
-- Chat2DB export data , export time: 2025-05-16 14:33:20
-- ----------------------------
SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for table pv_genre
-- ----------------------------
DROP TABLE IF EXISTS `pv_genre`;
CREATE TABLE `pv_genre` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `genre_name` varchar(64) NOT NULL COMMENT '类型名称',
  PRIMARY KEY (`id`)
) ORGANIZATION INDEX AUTO_INCREMENT = 46 AUTO_INCREMENT_MODE = 'ORDER' DEFAULT CHARSET = utf8mb4 ROW_FORMAT = DYNAMIC COMPRESSION = 'zstd_1.3.8' REPLICA_NUM = 1 BLOCK_SIZE = 16384 USE_BLOOM_FILTER = FALSE ENABLE_MACRO_BLOCK_BLOOM_FILTER = FALSE TABLET_SIZE = 134217728 PCTFREE = 0 COMMENT = '类型表';

-- ----------------------------
-- Table structure for table pv_movie
-- ----------------------------
DROP TABLE IF EXISTS `pv_movie`;
CREATE TABLE `pv_movie` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `movie_id` varchar(64) NOT NULL COMMENT '电影ID',
  `name` varchar(255) NOT NULL COMMENT '电影名称',
  `alias` varchar(512) DEFAULT NULL COMMENT '别名',
  `actors` varchar(1024) DEFAULT NULL COMMENT '演员列表',
  `cover` varchar(512) DEFAULT NULL COMMENT '封面URL',
  `directors` varchar(512) DEFAULT NULL COMMENT '导演列表',
  `douban_score` decimal(3,1) DEFAULT NULL COMMENT '豆瓣评分',
  `douban_votes` int(11) DEFAULT NULL COMMENT '豆瓣投票数',
  `genres` varchar(255) DEFAULT NULL COMMENT '类型',
  `imdb_id` varchar(32) DEFAULT NULL COMMENT 'IMDB ID',
  `languages` varchar(255) DEFAULT NULL COMMENT '语言',
  `mins` int(11) DEFAULT NULL COMMENT '时长(分钟)',
  `official_site` varchar(512) DEFAULT NULL COMMENT '官方网站',
  `regions` varchar(255) DEFAULT NULL COMMENT '地区',
  `release_date` datetime DEFAULT NULL COMMENT '上映日期',
  `slug` varchar(255) DEFAULT NULL COMMENT '短链标识',
  `storyline` text DEFAULT NULL COMMENT '剧情简介',
  `tags` varchar(512) DEFAULT NULL COMMENT '标签',
  `year` int(11) DEFAULT NULL COMMENT '年份',
  `actor_ids` varchar(1024) DEFAULT NULL COMMENT '演员ID列表',
  `director_ids` varchar(512) DEFAULT NULL COMMENT '导演ID列表',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_movie_id` (`movie_id`) BLOCK_SIZE 16384 LOCAL COMMENT '电影ID唯一索引',
  KEY `idx_name` (`name`) BLOCK_SIZE 16384 LOCAL COMMENT '电影名称索引',
  KEY `idx_year` (`year`) BLOCK_SIZE 16384 LOCAL COMMENT '年份索引'
) ORGANIZATION INDEX AUTO_INCREMENT = 50655 AUTO_INCREMENT_MODE = 'ORDER' DEFAULT CHARSET = utf8mb4 ROW_FORMAT = DYNAMIC COMPRESSION = 'zstd_1.3.8' REPLICA_NUM = 1 BLOCK_SIZE = 16384 USE_BLOOM_FILTER = FALSE ENABLE_MACRO_BLOCK_BLOOM_FILTER = FALSE TABLET_SIZE = 134217728 PCTFREE = 0 COMMENT = '电影信息表';

-- ----------------------------
-- Table structure for table pv_person
-- ----------------------------
DROP TABLE IF EXISTS `pv_person`;
CREATE TABLE `pv_person` (
  `person_id` bigint(20) NOT NULL COMMENT '名人ID',
  `name` varchar(255) NOT NULL COMMENT '演员名称',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `name_en` text DEFAULT NULL COMMENT '更多英文名',
  `name_zh` text DEFAULT NULL COMMENT '更多中文名',
  `birth_year` int(11) DEFAULT NULL COMMENT '出生年份',
  `birth_month` int(11) DEFAULT NULL COMMENT '出生月份',
  `birth_day` int(11) DEFAULT NULL COMMENT '出生日期',
  `birthplace` varchar(255) DEFAULT NULL COMMENT '出生地',
  `constellatory` varchar(50) DEFAULT NULL COMMENT '星座',
  `profession` varchar(255) DEFAULT NULL COMMENT '职业',
  `biography` text DEFAULT NULL COMMENT '简介，存在简介数据的名人只有15135个',
  PRIMARY KEY (`person_id`)
) ORGANIZATION INDEX DEFAULT CHARSET = utf8mb4 ROW_FORMAT = DYNAMIC COMPRESSION = 'zstd_1.3.8' REPLICA_NUM = 1 BLOCK_SIZE = 16384 USE_BLOOM_FILTER = FALSE ENABLE_MACRO_BLOCK_BLOOM_FILTER = FALSE TABLET_SIZE = 134217728 PCTFREE = 0 COMMENT = '名人信息表';

-- ----------------------------
-- Table structure for table pv_rating
-- ----------------------------
DROP TABLE IF EXISTS `pv_rating`;
CREATE TABLE `pv_rating` (
  `rating_id` bigint(20) NOT NULL COMMENT '评分ID',
  `user_id` varchar(128) DEFAULT NULL COMMENT '豆瓣用户ID（md5格式）',
  `movie_id` varchar(128) DEFAULT NULL COMMENT '电影ID，对应豆瓣的DOUBAN_ID',
  `rating` decimal(3,1) DEFAULT NULL COMMENT '评分',
  `rating_time` datetime DEFAULT NULL COMMENT '评分时间',
  PRIMARY KEY (`rating_id`)
) ORGANIZATION INDEX DEFAULT CHARSET = utf8mb4 ROW_FORMAT = DYNAMIC COMPRESSION = 'zstd_1.3.8' REPLICA_NUM = 1 BLOCK_SIZE = 16384 USE_BLOOM_FILTER = FALSE ENABLE_MACRO_BLOCK_BLOOM_FILTER = FALSE TABLET_SIZE = 134217728 PCTFREE = 0 COMMENT = '评分表';

-- ----------------------------
-- Table structure for table pv_user
-- ----------------------------
DROP TABLE IF EXISTS `pv_user`;
CREATE TABLE `pv_user` (
  `user_id` varchar(128) NOT NULL COMMENT '豆瓣用户ID',
  `user_nickname` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `user_account` varchar(255) DEFAULT NULL COMMENT '用户账号',
  `user_password` varchar(255) DEFAULT NULL COMMENT '用户密码',
  PRIMARY KEY (`user_id`)
) ORGANIZATION INDEX DEFAULT CHARSET = utf8mb4 ROW_FORMAT = DYNAMIC COMPRESSION = 'zstd_1.3.8' REPLICA_NUM = 1 BLOCK_SIZE = 16384 USE_BLOOM_FILTER = FALSE ENABLE_MACRO_BLOCK_BLOOM_FILTER = FALSE TABLET_SIZE = 134217728 PCTFREE = 0 COMMENT = '用户表';

-- ----------------------------
-- Table structure for table pv_user_like
-- ----------------------------
DROP TABLE IF EXISTS `pv_user_like`;
CREATE TABLE `pv_user_like` (
  `user_id` varchar(128) NOT NULL COMMENT '用户ID',
  `genre_id` bigint(20) NOT NULL COMMENT '类型ID'
) ORGANIZATION INDEX DEFAULT CHARSET = utf8mb4 ROW_FORMAT = DYNAMIC COMPRESSION = 'zstd_1.3.8' REPLICA_NUM = 1 BLOCK_SIZE = 16384 USE_BLOOM_FILTER = FALSE ENABLE_MACRO_BLOCK_BLOOM_FILTER = FALSE TABLET_SIZE = 134217728 PCTFREE = 0 COMMENT = '用户喜好表';

SET FOREIGN_KEY_CHECKS=1;

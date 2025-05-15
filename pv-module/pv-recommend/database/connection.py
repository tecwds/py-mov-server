import pymysql
from pymysql.cursors import DictCursor
from typing import Dict, Any
import logging
from threading import Lock
from config.config import ConfigManager

logger = logging.getLogger('MovieRecommenderAPI')


class DBConnectionPool:
    _instance = None
    _lock = Lock()

    def __new__(cls, db_config=None):
        if cls._instance is None:
            with cls._lock:
                if cls._instance is None:
                    cls._instance = super().__new__(cls)
                    cls._instance.pool = []
                    cls._instance.max_pool_size = 5
                    # 使用传入的配置或获取新配置
                    cls._instance.db_config = db_config if db_config else ConfigManager.get_db_config()
        return cls._instance

    def get_connection(self):
        """从连接池获取一个数据库连接"""
        try:
            if self.pool:
                conn = self.pool.pop()
                if self._check_connection(conn):
                    return conn
                else:
                    conn.close()

            return self._create_new_connection()
        except Exception as e:
            logger.error(f"从连接池获取连接失败: {str(e)}")
            return self._create_new_connection()

    def return_connection(self, conn):
        """将连接返回到连接池"""
        try:
            if len(self.pool) < self.max_pool_size and self._check_connection(conn):
                self.pool.append(conn)
            else:
                conn.close()
        except Exception as e:
            logger.error(f"将连接返回到连接池失败: {str(e)}")
            conn.close()

    def _create_new_connection(self):
        """创建新的数据库连接"""
        try:
            # 转换cursorclass字符串为实际类
            config = self.db_config.copy()
            if isinstance(config.get('cursorclass'), str):
                if config['cursorclass'] == 'DictCursor':
                    config['cursorclass'] = DictCursor

            conn = pymysql.connect(**config)
            logger.info("创建新的数据库连接")
            return conn
        except Exception as e:
            logger.error(f"创建新连接失败: {str(e)}")
            raise

    def _check_connection(self, conn):
        """检查连接是否有效"""
        try:
            with conn.cursor() as cursor:
                cursor.execute("SELECT 1")
                return True
        except Exception as e:
            logger.warning(f"数据库连接检查失败: {str(e)}")
            return False
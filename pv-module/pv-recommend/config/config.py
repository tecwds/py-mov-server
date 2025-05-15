import json
import os
from typing import Dict, Any

DEFAULT_APP_CONFIG = {
    'host': '0.0.0.0',
    'port': 5000,
    'debug': True
}

CONFIG_DIR = os.path.dirname(os.path.abspath(__file__))
DB_CONFIG_PATH = os.path.join(CONFIG_DIR, 'db_config.json')
APP_CONFIG_PATH = os.path.join(CONFIG_DIR, "app_config.json")

class ConfigManager:
    @staticmethod
    def load_db_config() -> Dict[str, Any]:
        """加载数据库配置"""
        if not os.path.exists(DB_CONFIG_PATH):
            print(f"请设置数据库配置")
            exit(1)

        try:
            with open(DB_CONFIG_PATH, 'r') as f:
                return json.load(f)
        except (json.JSONDecodeError, IOError) as e:
            print(f"加载数据库配置失败: {str(e)}")
            exit(1)

    @staticmethod
    def load_app_config() -> Dict[str, Any]:
        """加载应用配置"""
        if not os.path.exists(APP_CONFIG_PATH):
            print(f"未设置数据库配置，使用默认配置: {DEFAULT_APP_CONFIG}")
            return DEFAULT_APP_CONFIG

        try:
            with open(APP_CONFIG_PATH, 'r') as f:
                return json.load(f)
        except (json.JSONDecodeError, IOError) as e:
            print(f"加载应用配置失败: {str(e)}")
            exit(1)

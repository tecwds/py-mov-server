import json
import os
from typing import Dict, Any

DEFAULT_DB_CONFIG = {
    'host': 'localhost',
    'port': 3306,
    'user': 'user',
    'password': 'password',
    'database': 'databaseName',
    'charset': 'utf8mb4',
    'cursorclass': 'DictCursor',
    'autocommit': True
}

DEFAULT_APP_CONFIG = {
    'host': '0.0.0.0',
    'port': 5000,
    'debug': True
}

CONFIG_DIR = os.path.dirname(os.path.abspath(__file__))
DB_CONFIG_PATH = os.path.join(CONFIG_DIR, 'db_config.json')

class ConfigManager:
    @staticmethod
    def load_db_config() -> Dict[str, Any]:
        """加载数据库配置"""
        if not os.path.exists(DB_CONFIG_PATH):
            return {}

        try:
            with open(DB_CONFIG_PATH, 'r') as f:
                return json.load(f)
        except (json.JSONDecodeError, IOError) as e:
            print(f"加载数据库配置失败: {str(e)}")
            return {}

    @staticmethod
    def save_db_config(config: Dict[str, Any]) -> None:
        """保存数据库配置"""
        try:
            with open(DB_CONFIG_PATH, 'w') as f:
                json.dump(config, f, indent=4)
        except IOError as e:
            print(f"保存数据库配置失败: {str(e)}")

    @staticmethod
    def get_db_config() -> Dict[str, Any]:
        """获取数据库配置，如果不存在则提示用户输入"""
        config = ConfigManager.load_db_config()

        if not config:
            print("未找到现有的数据库配置。")
            config = ConfigManager.prompt_db_config()
            ConfigManager.save_db_config(config)
            return config

        print("\n当前数据库配置:")
        for key, value in config.items():
            print(f"{key}: {value}")

        choice = input("\n请选择:\n1. 使用当前配置\n2. 输入新配置\n> ")

        if choice == '1':
            return config
        elif choice == '2':
            new_config = ConfigManager.prompt_db_config()
            ConfigManager.save_db_config(new_config)
            return new_config
        else:
            print("无效选择，将使用当前配置")
            return config

    @staticmethod
    def prompt_db_config() -> Dict[str, Any]:
        """提示用户输入数据库配置"""
        print("\n请输入数据库配置:")
        config = DEFAULT_DB_CONFIG.copy()

        for key in ['host', 'user', 'password', 'database']:
            value = input(f"{key} [{config[key]}]: ").strip()
            if value:
                config[key] = value

        port = input(f"端口 [{config['port']}]: ").strip()
        if port:
            try:
                config['port'] = int(port)
            except ValueError:
                print(f"无效端口，将使用默认值 {config['port']}")

        return config

    @staticmethod
    def get_app_config() -> Dict[str, Any]:
        """获取应用配置（带默认值提示）"""
        print("\n=== 应用运行参数配置 ===")
        print("请输入以下参数（直接回车使用默认值）")

        config = DEFAULT_APP_CONFIG.copy()

        # 主机配置
        host = input(f"主机地址 [{config['host']}]: ").strip()
        if host:
            # 简单验证IP格式
            if len(host.split('.')) == 4 and all(p.isdigit() and 0 <= int(p) <= 255 for p in host.split('.')):
                config['host'] = host
            else:
                print(f"! 使用默认主机地址: {config['host']}")

        # 端口配置
        port = input(f"端口号 [{config['port']}]: ").strip()
        if port:
            try:
                port = int(port)
                if 1024 <= port <= 65535:
                    config['port'] = port
                else:
                    print(f"! 端口超出范围，使用默认: {config['port']}")
            except ValueError:
                print(f"! 无效端口，使用默认: {config['port']}")

        # 调试模式
        debug = input(f"启用调试模式? (y/n) [{'y' if config['debug'] else 'n'}]: ").strip().lower()
        if debug:
            config['debug'] = debug in ('y', 'yes')

        return config
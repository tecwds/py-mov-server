# PythonWeb 推荐系统

**电影推荐系统**

模块分为：

- Java 应用后端
- Python 推荐后端

## 配置文件

### Java `application.yml`

参考 `pv-app/src/main/resources/application.yml`

### Python `config.json`

总体来说，需要设置数据库和应用配置，具体参考：

- 应用配置：`pv-module/pv-recommend/config/app_config.json.example`
- 数据库配置：`pv-module/pv-recommend/config/db_config.json.example`

Tips:

- 配置文件需要去掉 `example` 后缀
- 必须配置数据库配置，否则容器无法正常启动

## 如何启动

这里只提供 docker 形式，注意需要高版本（建议最新版）Docker

```shell
docker compose -f compose.yml up -d
```

Tips:

- 可能存在网络问题，如出现问题，需自行设置代理
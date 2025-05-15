FROM debian:trixie

WORKDIR /app

# 更新 apt 缓存
RUN apt update -y && apt install curl -y

# 安装 uv
RUN curl -LsSf https://astral.sh/uv/install.sh | sh

# copy 代码
COPY . .

# 使用 Python 3.10 并同步依赖
RUN /root/.local/bin/uv venv --python 3.10 && /root/.local/bin/uv sync

# 运行
ENTRYPOINT ["/root/.local/bin/uv", "run", "app.py"]



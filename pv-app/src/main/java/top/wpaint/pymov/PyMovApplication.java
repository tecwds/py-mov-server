package top.wpaint.pymov;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@MapperScan("top.wpaint.pymov.mapper")
public class PyMovApplication {

    public static void main(String[] args) {
        SpringApplication.run(PyMovApplication.class, args);
        log.info("载入记忆成功，你是数据人，辅助推荐系统 PyMov 启动成功...");
    }
}

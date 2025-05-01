package top.wpaint.pymov.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class AppConfig {

    @Value("${jwt.secret-key}")
    private String tokenSecretKey;
}

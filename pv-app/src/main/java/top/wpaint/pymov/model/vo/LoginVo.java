package top.wpaint.pymov.model.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class LoginVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 7441209578693112852L;

    /**
     * Jwt Token
     */
    private String token;

    /**
     * 用户信息
     */
    private UserVo user;
}

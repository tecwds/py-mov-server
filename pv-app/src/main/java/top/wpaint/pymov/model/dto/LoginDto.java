package top.wpaint.pymov.model.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class LoginDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 725353406088647105L;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;
}

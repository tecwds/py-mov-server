package top.wpaint.pymov.model.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class RegistryDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 6268134793501145551L;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String password;

    /**
     * 重复密码
     */
    private String rePassword;
}

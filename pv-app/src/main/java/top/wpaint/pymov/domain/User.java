package top.wpaint.pymov.domain;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName pv_user
 */
@Data
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一标识ID，长整型
     */
    private Long userId;

    /**
     * 性别：M-男，F-女，U-未知
     */
    private String gender;

    /**
     * 用户年龄，整数型
     */
    private Integer age;

    /**
     * 职业编码，整数型
     */
    private Integer occupation;

    /**
     * 邮政编码，字符串类型
     */
    private String zipCode;

    /**
     * 用户名，字符串类型
     */
    private String username;

    /**
     * 用户昵称，字符串类型
     */
    private String nickname;

    /**
     * 密码，加密存储字符串
     */
    private String password;

    /**
     * 电子邮箱地址，字符串类型
     */
    private String email;
}
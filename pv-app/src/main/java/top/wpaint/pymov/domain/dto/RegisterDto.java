package top.wpaint.pymov.domain.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class RegisterDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String email;
    private String username;
    private String password;
    private String nickname;
    private String gender;
    private Integer age;

    /**
     * 职业
     */
    private Integer occupation;

    /**
     * 地区编码
     */
    private String zipCode;

    private List<Long> likes;
}

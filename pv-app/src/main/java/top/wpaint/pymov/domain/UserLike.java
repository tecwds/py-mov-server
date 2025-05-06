package top.wpaint.pymov.domain;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName pv_user_like
 */
@Data
public class UserLike implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 用户唯一标识ID，长整型
     */
    private Long userId;

    /**
     * 电影类型唯一标识ID
     */
    private Integer genreId;
}
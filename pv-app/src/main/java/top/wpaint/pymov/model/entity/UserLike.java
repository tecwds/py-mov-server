package top.wpaint.pymov.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName pv_user_like
 */
@TableName(value ="pv_user_like")
@Data
public class UserLike implements Serializable {
    /**
     * 用户ID
     */
    private String user_id;

    /**
     * 类型ID
     */
    private Long genre_id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
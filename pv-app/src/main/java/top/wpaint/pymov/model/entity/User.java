package top.wpaint.pymov.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName pv_user
 */
@TableName(value ="pv_user")
@Data
public class User implements Serializable {
    /**
     * 豆瓣用户ID
     */
    @TableId
    private String user_id;

    /**
     * 用户昵称
     */
    private String user_nickname;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
package top.wpaint.pymov.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName pv_rating
 */
@TableName(value ="pv_rating")
@Data
public class Rating implements Serializable {
    /**
     * 评分ID
     */
    @TableId
    private Long rating_id;

    /**
     * 豆瓣用户ID（md5格式）
     */
    private String user_id;

    /**
     * 电影ID，对应豆瓣的DOUBAN_ID
     */
    private String movie_id;

    /**
     * 评分
     */
    private BigDecimal rating;

    /**
     * 评分时间
     */
    private Date rating_time;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
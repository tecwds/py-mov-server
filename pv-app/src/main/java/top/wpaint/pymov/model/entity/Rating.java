package top.wpaint.pymov.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
    private Long ratingId;

    /**
     * 豆瓣用户ID（md5格式）
     */
    private String userId;

    /**
     * 电影ID，对应豆瓣的DOUBAN_ID
     */
    private String movieId;

    /**
     * 评分
     */
    private BigDecimal rating;

    /**
     * 评分时间
     */
    private Date ratingTime;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
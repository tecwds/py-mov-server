package top.wpaint.pymov.domain;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName pv_movie_rating
 */
@Data
public class MovieRating implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 评分记录的唯一标识ID
     */
    private Long ratingId;

    /**
     * 用户唯一标识ID，关联用户基础信息表 pv_user
     */
    private Long userId;

    /**
     * 电影唯一标识ID，关联电影信息表 pv_movie
     */
    private Long movieId;

    /**
     * 用户评分，通常取值范围为1到5或1到10
     */
    private Integer rating;

    /**
     * 评分时间戳，单位为毫秒
     */
    private Long timestamp;
}
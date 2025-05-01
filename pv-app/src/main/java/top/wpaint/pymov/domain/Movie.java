package top.wpaint.pymov.domain;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName pv_movie
 */
@Data
public class Movie implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 电影唯一标识ID，长整型
     */
    private Long movieId;

    /**
     * 电影名称，字符串类型
     */
    private String title;

    /**
     * 电影发布年份，整数型
     */
    private Integer pubYear;
}
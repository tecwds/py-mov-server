package top.wpaint.pymov.domain;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName pv_movie_genres
 */
@Data
public class MovieGenres implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 电影唯一标识ID，长整型
     */
    private Long movieId;

    /**
     * 类型唯一标识ID
     */
    private Integer genreId;
}
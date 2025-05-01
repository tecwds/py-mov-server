package top.wpaint.pymov.domain;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName pv_genres_dict
 */
@Data
public class GenresDict implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识ID
     */
    private Integer genreId;

    /**
     * 类型英文名称
     */
    private String genreNameEn;

    /**
     * 类型中文名称
     */
    private String genreNameCn;
}
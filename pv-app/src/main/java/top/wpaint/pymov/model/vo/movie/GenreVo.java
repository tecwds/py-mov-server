package top.wpaint.pymov.model.vo.movie;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class GenreVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 2173192836165270676L;

    /**
     * 类型 ID
     */
    private Long id;

    /**
     * 类型名称
     */
    private String name;
}

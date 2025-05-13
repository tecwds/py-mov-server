package top.wpaint.pymov.model.vo.user.like;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class LikeVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 989639570632552740L;

    /**
     * 类型 ID
     */
    private Long genreId;

    /**
     * 类型名
     */
    private String genreName;
}

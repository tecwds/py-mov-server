package top.wpaint.pymov.model.dto.user.like;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class LikeEditDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 7786158405123617130L;

    /**
     * 用户 ID
     */
    private String userId;

    /**
     * 需要删除的标签
     */
    private List<Long> subGenreIds;

    /**
     * 需要增加的标签
     */
    private List<Long> addGenreIds;
}

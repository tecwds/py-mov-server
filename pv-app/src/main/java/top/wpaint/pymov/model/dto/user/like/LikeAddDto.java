package top.wpaint.pymov.model.dto.user.like;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class LikeAddDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 5579017325468065177L;

    /**
     * 用户 ID
     */
    private String userId;

    /**
     * 用户喜好的电影类型 ID
     */
    private List<Long> likeGenreIds;
}

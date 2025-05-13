package top.wpaint.pymov.model.dto.user.like;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class LikeDeleteDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 4489562782513640243L;

    private String userId;

    private List<Long> genreIds;
}

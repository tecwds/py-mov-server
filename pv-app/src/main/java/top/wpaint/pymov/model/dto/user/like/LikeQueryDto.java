package top.wpaint.pymov.model.dto.user.like;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.wpaint.pymov.common.RPage;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class LikeQueryDto extends RPage implements Serializable {

    @Serial
    private static final long serialVersionUID = 5031164177470293605L;

    private String userId;
}

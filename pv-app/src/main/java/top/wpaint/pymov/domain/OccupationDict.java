package top.wpaint.pymov.domain;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName pv_occupation_dict
 */
@Data
public class OccupationDict implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 职业编码ID(0-20)
     */
    private Integer occupationId;

    /**
     * 英文职业名称
     */
    private String enName;

    /**
     * 中文职业名称
     */
    private String cnName;
}
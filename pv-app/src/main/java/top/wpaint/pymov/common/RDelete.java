package top.wpaint.pymov.common;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 通用删除请求
 */
@Data
public class RDelete implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;
}

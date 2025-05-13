package top.wpaint.pymov.common;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 通用分页请求
 */
@Data
public class RPage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 当前页号
     */
    private int current = 1;

    /**
     * 每页大小
     */
    private int pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（desc,asc）
     */
    private String sortOrder = "desc";
}

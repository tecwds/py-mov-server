package top.wpaint.pymov.common;

import lombok.Data;

@Data
public class QueryRange<T> {
    private T min;
    private T max;
}

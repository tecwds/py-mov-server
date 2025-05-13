package top.wpaint.pymov.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 全局返回类
 *
 * @param <T> 返回的类型
 */
@Data
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class R<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private T data;

    public static <T> R<T> ok() {
        return new R<>(RCode.SUCCESS.getCode(), RCode.SUCCESS.getMessage(), null);
    }

    public static <T> R<T> ok(RCode rcode, T data) {
        return new R<>(rcode.getCode(), rcode.getMessage(), data);
    }

    public static <T> R<T> ok(T data) {
        return new R<>(RCode.SUCCESS.getCode(), RCode.SUCCESS.getMessage(), data);
    }

    public static <T> R<T> fail(RCode rcode) {
        return new R<>(rcode.getCode(), rcode.getMessage(), null);
    }

    public static <T> R<T> fail(int code, String msg) {
        return new R<>(code, msg, null);
    }

    public static <T> R<T> fail(RCode rCode, String msg) {
        return new R<>(rCode.getCode(), msg, null);
    }
}

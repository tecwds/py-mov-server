package top.wpaint.pymov.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class R<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static final int SUCCESS = 0;
    public static final int FAILURE = 1;

    private String msg;
    private int code;
    private T data;

    public static R<Void> ok() {
        return new R<>("操作成功", SUCCESS, null);
    }

    public static R<Void> ok(String msg) {
        return new R<>(msg, SUCCESS, null);
    }

    public static  <T> R<T> ok(T data) {
        return new R<>("操作成功", SUCCESS, data);
    }

    public static  <T> R<T> ok(String msg, T data) {
        return new R<>(msg, SUCCESS, data);
    }

    public static R<Void> fail() {
        return new R<>("操作失败", FAILURE, null);
    }

    public static R<Void> fail(String msg) {
        return new R<>(msg, FAILURE, null);
    }
}

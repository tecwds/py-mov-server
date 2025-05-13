package top.wpaint.pymov.utils;

import lombok.NoArgsConstructor;
import top.wpaint.pymov.common.RCode;
import top.wpaint.pymov.common.exception.PvException;

/**
 * 异常处理工具类
 */
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ThrowUtils {

    /**
     * 条件成立则抛异常
     * @param condition 条件
     * @param exception 异常
     */
    public static void throwIf(boolean condition, RuntimeException exception) {
        if (condition) {
            throw exception;
        }
    }

    /**
     * 条件成立则抛异常
     * @param condition 条件
     * @param rCode 错误码枚举类
     */
    public static void throwIf(boolean condition, RCode rCode) {
        throwIf(condition, new PvException(rCode));
    }

    /**
     * 条件成立则抛异常
     * @param condition 条件
     * @param rCode 错误码
     * @param message   错误信息
     */
    public static void throwIf(boolean condition, RCode rCode, String message) {
        throwIf(condition, new PvException(rCode, message));
    }
}

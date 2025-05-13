package top.wpaint.pymov.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import top.wpaint.pymov.common.RCode;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidUtils {

    /**
     * 不能为空
     *
     * @param obj 需要检查的对象
     */
    public static void requireNotNull(Object obj) {
        ThrowUtils.throwIf(obj == null, RCode.PARAMS_ERROR);
    }
}

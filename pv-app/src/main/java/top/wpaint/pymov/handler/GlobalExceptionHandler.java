package top.wpaint.pymov.handler;

import cn.dev33.satoken.exception.SaTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.wpaint.pymov.common.R;
import top.wpaint.pymov.common.RCode;
import top.wpaint.pymov.common.exception.PvException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常统一处理
     * @param e 业务异常
     * @return R<String>
     */
    @ExceptionHandler(PvException.class)
    public R<String> businessExceptionHandler(PvException e) {
        log.error("PvException: {}", e.getMessage());
        return R.fail(e.getCode(), e.getMessage());
    }

    /**
     * SaToken异常统一处理
     * @param e 异常
     * @return R<String>
     */
    @ExceptionHandler(SaTokenException.class)
    public R<String> saExceptionHandler(SaTokenException e) {
        log.error("SaTokenException: {}", e.getMessage());
        return R.fail(e.getCode(), e.getMessage());
    }

    /**
     * 系统异常统一处理
     * @param e 通用异常
     * @return R<String>
     */
    @ExceptionHandler(Exception.class)
    public R<String> runtimeExceptionHandler(Exception e) {
        log.error("Exception: {}", e.getMessage());
        return R.fail(RCode.SYSTEM_ERROR);
    }
}

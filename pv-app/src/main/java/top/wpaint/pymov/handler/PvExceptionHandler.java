package top.wpaint.pymov.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.wpaint.pymov.common.R;
import top.wpaint.pymov.common.exception.PvException;

@Slf4j
@ControllerAdvice
public class PvExceptionHandler {

    @ResponseBody
    @ExceptionHandler(PvException.class)
    public R<Void> pvExceptionHandler(PvException e) {
        log.info("大脑异常: {}", e.getMessage());
        return R.fail(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public R<Void> exceptionHandler(Exception e) {
        log.info("大脑过载: {}", e.getMessage());
        return R.fail();
    }
}

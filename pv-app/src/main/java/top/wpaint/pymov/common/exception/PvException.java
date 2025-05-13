package top.wpaint.pymov.common.exception;

import lombok.Getter;
import top.wpaint.pymov.common.RCode;

public class PvException extends RuntimeException {
    /**
     * 错误码
     */
    @Getter
    private final int code;

    public PvException(int code, String message) {
        super(message);
        this.code = code;
    }

    public PvException(RCode rCode) {
        super(rCode.getMessage());
        this.code = rCode.getCode();
    }

    public PvException(RCode rCode, String message) {
        super(message);
        this.code = rCode.getCode();
    }
}

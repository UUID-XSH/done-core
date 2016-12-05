package info.xsh.done.core.exception;

import lombok.Getter;

/**
 * Created by yann on 2016/12/5.
 */

@Getter
public class DoneProjectException extends RuntimeException {

    private String errorCode;
    private String errorMsg;

    public DoneProjectException(ExceptionCode exceptionCode, Object... formatParams) {
        this.errorCode = exceptionCode.getErrorCode();
        this.errorMsg = String.format(exceptionCode.getErrorMsg(), formatParams);
    }
}

package info.xsh.done.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Created by yann on 2016/12/5.
 */

@Getter
public class DoneProjectException extends RuntimeException {

    private String errorCode;
    private String errorMsg;
    private HttpStatus httpStatus;

    public DoneProjectException(ExceptionCode exceptionCode, Object... formatParams) {
        this.errorCode = exceptionCode.getErrorCode();
        this.httpStatus = exceptionCode.getHttpStatus();
        this.errorMsg = String.format(exceptionCode.getErrorMsg(), formatParams);
    }
}

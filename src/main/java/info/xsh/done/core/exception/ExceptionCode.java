package info.xsh.done.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Created by yann on 2016/12/5.
 */

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    UN_KNOW("9999", "unknow exception", HttpStatus.BAD_REQUEST),
    NOT_FOUND("00007", "not found", HttpStatus.BAD_REQUEST);

    private String errorCode;
    private String errorMsg;
    private HttpStatus httpStatus;
}

package info.xsh.done.core.config;

import info.xsh.done.core.exception.DoneProjectException;
import info.xsh.done.core.exception.ErrorInfo;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yann on 2016/12/5.
 */

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ErrorInfo defaultErrorHandler(HttpServletRequest req,
                                         HttpServletResponse response,
                                         Exception e) throws Exception {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            response.setStatus(responseStatus.code().value());
        }else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }

        if (e instanceof DoneProjectException) {
            DoneProjectException doneProjectException = (DoneProjectException) e;
            return new ErrorInfo(doneProjectException.getErrorCode(), doneProjectException.getErrorMsg());
        }

        return new ErrorInfo("99999", e.getMessage());
    }
}

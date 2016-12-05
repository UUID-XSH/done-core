package info.xsh.done.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by yann on 2016/12/5.
 */

@Getter
@AllArgsConstructor
public class ErrorInfo {
    private String code;
    private String msg;
}

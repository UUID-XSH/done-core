package info.xsh.done.core.validator;

import info.xsh.done.core.controller.vo.UserVo;
import info.xsh.done.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yangxueying on 2016/12/8.
 */
public class CommonValidator {

    @Autowired
    private static UserService userService;

    public static String validateUserCreateForm(UserVo form) {
        if (form.getPassword().equals(form.getPasswordRepeated()) && !userService.getUserByEmail(form.getEmail()).isPresent()) {
            return "";
        }
        return "VALIDATION_FAIL";
    }
}

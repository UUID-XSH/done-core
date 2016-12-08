package info.xsh.done.core.validator;

import info.xsh.done.core.controller.vo.UserVo;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by yangxueying on 2016/12/7.
 */
public class UserCreateFormValidator implements ConstraintValidator<UserCreateFormAnnotation, UserVo> {

    UserCreateFormAnnotation userCreateFormAnnotation;

    public void initialize(UserCreateFormAnnotation userCreateFormAnnotation) {
        this.userCreateFormAnnotation = userCreateFormAnnotation;
    }

    public boolean isValid(UserVo form, ConstraintValidatorContext context) {
        if (this.userCreateFormAnnotation.nullable() && null==form) {
            return true;
        }
        String message = CommonValidator.validateUserCreateForm(form);
        return StringUtils.isBlank(message);
    }

}

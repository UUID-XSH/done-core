package info.xsh.done.core.controller.vo;

import info.xsh.done.core.domain.Role;
import info.xsh.done.core.validator.UserCreateFormAnnotation;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by yangxueying on 2016/12/1.
 */
@Data
@UserCreateFormAnnotation(message = "UserVo校验错误")
public class UserVo {

    private Long id; //用户id

    @NotEmpty(message = "用户名不能为空")
    private String name; //用户名

    @NotEmpty(message = "用户密码不能为空")
    private String password; //用户密码

    @NotEmpty(message = "用户重复密码不能为空")
    private String passwordRepeated;

    private String nickName;

    @NotEmpty(message = "email不能为空")
    private String email;

    @NotNull(message = "用户角色不能为空")
    private Role role = Role.USER;

    private LocalDateTime registerDate; // 创建时间

    private LocalDateTime recentLoginTime; // 登录时间
}

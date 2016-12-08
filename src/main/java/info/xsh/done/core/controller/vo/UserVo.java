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
@UserCreateFormAnnotation
public class UserVo {

    private Long id; //用户id

    @NotEmpty(message = "1111")
    private String name; //用户名

    @NotEmpty(message = "1111")
    private String password; //用户密码

    @NotEmpty
    private String passwordRepeated;

    private String nickName;

    @NotEmpty
    private String email;

    @NotNull
    private Role role = Role.USER;

    private LocalDateTime registerDate; // 创建时间

    private LocalDateTime recentLoginTime; // 登录时间
}

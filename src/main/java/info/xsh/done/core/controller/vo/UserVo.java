package info.xsh.done.core.controller.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by yangxueying on 2016/12/1.
 */
@Data
public class UserVo {
    private Long id; //用户id
    private String name; //用户名
    private String passWord; //用户密码
    private String nickName;
    private String email;
    private LocalDateTime registerDate; // 创建时间
    private LocalDateTime recentLoginTime; // 登录时间
}

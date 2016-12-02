package info.xsh.done.core.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by xiaohuo on 16/11/29.
 */
@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name",unique=true)
    private String name;
    @Column(name = "pass_word")
    private String passWord;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "email")
    private String email;
    @Column(name = "register_date")
    private LocalDateTime registerDate=LocalDateTime.now(); // 创建时间
    @Column(name = "recent_login_time")
    private LocalDateTime recentLoginTime=LocalDateTime.now(); // 登录时间

}

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
public class User extends BaseDomain {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "pass_word")
    private String password;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "register_at")
    private LocalDateTime registerDate; // 创建时间

    @Column(name = "recent_login_time_at")
    private LocalDateTime recentLoginTime; // 登录时间

    @PrePersist
    public void prePersist() {
        registerDate = LocalDateTime.now();
        recentLoginTime = LocalDateTime.now();
        super.prePersist();
    }

    @PreUpdate
    public void preUpdate() {
        recentLoginTime = LocalDateTime.now();
        super.preUpdate();
    }

}

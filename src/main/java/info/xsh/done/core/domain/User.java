package info.xsh.done.core.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by xiaohuo on 16/11/29.
 */
@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "pass_word")
    private String passWord;
}

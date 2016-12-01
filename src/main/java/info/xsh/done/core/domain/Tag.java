package info.xsh.done.core.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by xiaohuo on 16/11/30.
 */
@Entity
@Data
public class Tag {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "user_id")
    private long userId;
}

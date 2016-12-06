package info.xsh.done.core.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by xiaohuo on 16/11/30.
 */
@Entity
@Data
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_id")
    private long userId;
}

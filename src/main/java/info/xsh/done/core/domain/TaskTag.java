package info.xsh.done.core.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by xiaohuo on 16/12/7.
 */
@Entity
@Table(name = "task_tag")
@Data
public class TaskTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "task_id")
    private long taskId;
    @Column(name = "tag_id")
    private long tagId;
}

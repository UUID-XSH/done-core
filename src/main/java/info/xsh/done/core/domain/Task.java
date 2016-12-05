package info.xsh.done.core.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by xiaohuo on 16/11/28.
 */
@Entity
@Table(name = "task")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private Long id;
    @Column(name = "name")
    private String name; // 任务名称
    @Column(name = "detail")
    private String detail; // 详情
    @Column(name = "is_achieved")
    @Enumerated(EnumType.STRING)
    private Project.YesOrNo isAchieved = Project.YesOrNo.NO; //是否完成
    @Column(name = "project_id", nullable = false)
    private Long projectId;

}

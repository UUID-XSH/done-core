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
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name; // 任务名称
    @Column(name = "detail")
    private String detail; // 详情
    @Column(name = "is_achieved")
    @Enumerated(EnumType.STRING)
    private YesOrNo isAchieved = YesOrNo.NO; //是否完成
    @Enumerated(EnumType.STRING)
    @Column(name = "is_final")
    private YesOrNo isFinal = YesOrNo.NO;    //是否是最小执行单位
    @Column(name = "project_id")
    private long projectId;

}

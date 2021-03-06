package info.xsh.done.core.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by yangxueying on 2016/12/3.
 */
@Entity
@Table(name = "single_task")
@Data
public class SingleTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name; // 任务名称

    @Column(name = "detail")
    private String detail; // 详情

    @Column(name = "is_achieved")
    @Enumerated(EnumType.STRING)
    private YesOrNo isAchieved = YesOrNo.NO; //是否完成

    @Column(name = "user_id", nullable = false)
    private Long userId;
}

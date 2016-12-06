package info.xsh.done.core.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by yangxueying on 2016/12/6.
 */
@Entity
@Table(name = "tomato")
@Data
public class Timer extends BaseDomain {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
    private Long taskId;

    @Column(name = "start_at")
    private LocalDateTime startDate; // 开始时间

    @Column(name = "end_at")
    private LocalDateTime endTime; // 结束时间

    @Column(name = "tomato_time")
    private int tomatoTime = 25; // 持续时间

    @Column(name = "is_canceled")
    @Enumerated(EnumType.STRING)
    private YesOrNo isCanceled = YesOrNo.NO; //是否取消

    @Column(name = "is_achieved")
    @Enumerated(EnumType.STRING)
    private YesOrNo isAchieved = YesOrNo.NO; //是否结束


    @PrePersist
    public void prePersist() {
        startDate = LocalDateTime.now();
        super.prePersist();
    }

    @PreUpdate
    public void preUpdate() {
        endTime = LocalDateTime.now();
        super.preUpdate();
    }
}

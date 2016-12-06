package info.xsh.done.core.controller.vo;

import info.xsh.done.core.domain.YesOrNo;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by yangxueying on 2016/12/6.
 */
@Data
public class TimerVo {
    private Long taskId;
    private LocalDateTime startDate; // 开始时间
    private LocalDateTime endTime; // 结束时间
    private int tomatoTime;
    private YesOrNo isCanceled;
    private YesOrNo isAchieved;
}

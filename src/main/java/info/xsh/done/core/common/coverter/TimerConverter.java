package info.xsh.done.core.common.coverter;

import info.xsh.done.core.controller.vo.TimerVo;
import info.xsh.done.core.domain.Timer;
import info.yannxia.java.chameleon.annonation.Convertor;
import org.springframework.stereotype.Component;

/**
 * Created by yangxueying on 2016/12/6.
 */
@Component
public class TimerConverter {

    @Convertor
    public Timer doForward(TimerVo timerVo) {
        Timer timer = new Timer();
        timer.setStartDate(timerVo.getStartDate());
        timer.setEndTime(timerVo.getEndTime());
        timer.setTaskId(timerVo.getTaskId());
        timer.setTomatoTime(timerVo.getTomatoTime());
        timer.setIsCanceled(timerVo.getIsCanceled());
        return timer;
    }

    @Convertor
    public TimerVo doBackward(Timer timer) {
        TimerVo timerVo = new TimerVo();
        timerVo.setTaskId(timer.getTaskId());
        timerVo.setStartDate(timer.getStartDate());
        timerVo.setEndTime(timer.getEndTime());
        timerVo.setId(timer.getId());
        timerVo.setTomatoTime(timer.getTomatoTime());
        timerVo.setIsCanceled(timer.getIsCanceled());
        return timerVo;
    }
}

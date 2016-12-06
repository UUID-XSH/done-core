package info.xsh.done.core.controller;

import info.xsh.done.core.controller.vo.TimerVo;
import info.xsh.done.core.domain.Timer;
import info.xsh.done.core.domain.YesOrNo;
import info.xsh.done.core.exception.DoneProjectException;
import info.xsh.done.core.exception.ExceptionCode;
import info.xsh.done.core.service.TimerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * author : misha
 */
@RestController
@RequestMapping(value = "/api/v1.0", produces = "application/json")
@Slf4j
public class TimerController extends BaseController {

    @Autowired
    private TimerService timerService;

    /**
     * 创建任务计时
     *
     * @param taskId
     * @param timerVo
     * @return
     */
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "tasks/{taskId}/timer", method = RequestMethod.POST)
    public TimerVo create(@PathVariable Long taskId, @RequestBody TimerVo timerVo) {
        Timer timer = convert(Timer.class, timerVo);
        timer.setTaskId(taskId);
        return convert(TimerVo.class, timerService.save(timer));
    }

    @RequestMapping(value = "tasks/{taskId}/timer", method = RequestMethod.DELETE)
    public TimerVo cancel(@PathVariable Long taskId) {
        Timer timer = timerService.findByTaskId(taskId).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "timer不存在！"));
        timer.setIsCanceled(YesOrNo.YES);
        return convert(TimerVo.class, timerService.save(timer));
    }

    @RequestMapping(value = "tasks/{taskId}/timer/achieve", method = RequestMethod.POST)
    public TimerVo setAchieve(@PathVariable Long taskId) {
        Timer timer = timerService.findByTaskId(taskId).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "timer不存在！"));
        timer.setIsAchieved(YesOrNo.YES);
        return convert(TimerVo.class, timerService.save(timer));
    }

    @RequestMapping(value = "tasks/{taskId}/timers", method = RequestMethod.GET)
    public TimerVo find(@PathVariable Long taskId) {
        Timer timer = timerService.findByTaskId(taskId).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "timer不存在！"));
        return convert(TimerVo.class, timer);
    }


}

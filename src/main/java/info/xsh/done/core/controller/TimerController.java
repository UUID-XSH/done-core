package info.xsh.done.core.controller;

import info.xsh.done.core.controller.vo.TimerVo;
import info.xsh.done.core.domain.Task;
import info.xsh.done.core.domain.Timer;
import info.xsh.done.core.domain.YesOrNo;
import info.xsh.done.core.exception.DoneProjectException;
import info.xsh.done.core.exception.ExceptionCode;
import info.xsh.done.core.service.TaskService;
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

    @Autowired
    private TaskService taskService;

    /**
     * 创建任务计时
     *
     * @param taskId
     * @param userId
     * @param projectId
     * @param timerVo
     * @return
     */
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "users/{userId}/projects/{projectId}/tasks/{taskId}/timer", method = RequestMethod.POST)
    public TimerVo create(@PathVariable Long taskId, @PathVariable Long userId, @PathVariable Long projectId, @RequestBody TimerVo timerVo) {
        Timer timer = convert(Timer.class, timerVo);
        Task task = taskService.findByUserIdAndProjectIdAndTaskId(userId, projectId, taskId).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "task不存在！"));
        timer.setTaskId(task.getId());
        return convert(TimerVo.class, timerService.save(timer));
    }

    /**
     * 取消计时
     *
     * @param taskId
     * @param userId
     * @param projectId
     * @return
     */
    @RequestMapping(value = "users/{userId}/projects/{projectId}/tasks/{taskId}/timer", method = RequestMethod.DELETE)
    public TimerVo cancel(@PathVariable Long taskId, @PathVariable Long userId, @PathVariable Long projectId) {
        Timer timer = timerService.findByUserIdAndProjectIdAndTaskId(userId, projectId, taskId).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "timer不存在！"));
        timer.setIsCanceled(YesOrNo.YES);
        return convert(TimerVo.class, timerService.save(timer));
    }

    /**
     * 设置完成
     *
     * @param taskId
     * @param userId
     * @param projectId
     * @return
     */
    @RequestMapping(value = "users/{userId}/projects/{projectId}/tasks/{taskId}/timer/achieve", method = RequestMethod.POST)
    public TimerVo setAchieve(@PathVariable Long taskId, @PathVariable Long userId, @PathVariable Long projectId) {
        Timer timer = timerService.findByUserIdAndProjectIdAndTaskId(userId, projectId, taskId).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "timer不存在！"));
        timer.setIsAchieved(YesOrNo.YES);
        return convert(TimerVo.class, timerService.save(timer));
    }

    /**
     * 查询timer
     *
     * @param taskId
     * @param userId
     * @param projectId
     * @return
     */
    @RequestMapping(value = "users/{userId}/projects/{projectId}/tasks/{taskId}/timer", method = RequestMethod.GET)
    public TimerVo find(@PathVariable Long taskId, @PathVariable Long userId, @PathVariable Long projectId) {
        Timer timer = timerService.findByUserIdAndProjectIdAndTaskId(userId, projectId, taskId).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "timer不存在！"));
        return convert(TimerVo.class, timer);
    }


}

package info.xsh.done.core.controller;

import info.xsh.done.core.controller.vo.SingleTaskVo;
import info.xsh.done.core.domain.Project;
import info.xsh.done.core.domain.SingleTask;
import info.xsh.done.core.domain.User;
import info.xsh.done.core.service.TaskService;
import info.xsh.done.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaohuo on 16/11/28.
 */
@RestController
@RequestMapping(value = "/api/v1.0", produces = "application/json")
@Slf4j
public class TaskController extends BaseController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    /**
     * 保存独立task
     *
     * @param singleTaskVo
     * @param userId
     * @return
     */
    @RequestMapping(value = "users/{userId}/tasks", method = RequestMethod.POST)
    public SingleTaskVo save(@RequestBody SingleTaskVo singleTaskVo, @PathVariable String userId) {
        SingleTask singleTask = convertFactory().convert(SingleTask.class, singleTaskVo);
        User user = userService.findById(userId).orElseThrow(() -> new IllegalArgumentException("用户不存在"));
        singleTask.setUserId(user.getId());
        singleTask = taskService.save(singleTask);
        log.info(String.format("Single task have been save: %s", singleTask.toString()));
        return convertFactory().convert(SingleTaskVo.class, singleTask);
    }

    /**查询指定独立task
     * @param userId
     * @param taskId
     * @return
     */
    @RequestMapping(value = "users/{userId}/single_task/{taskId}", method = RequestMethod.GET)
    public SingleTaskVo getSingleTask(@PathVariable String userId, @PathVariable String taskId) {
        SingleTask singleTask = taskService.getById(taskId).orElseThrow(() -> new IllegalArgumentException("任务不存在!"));
        if (singleTask.getUserId() != Long.valueOf(userId)) {
            throw new IllegalArgumentException("任务不属于该用户!");
        }
        return convertFactory().convert(SingleTaskVo.class, singleTask);
    }

    /**显示用户所有独立task
     * @param userId
     * @return
     */
    @RequestMapping(value = "users/{userId}/single_task", method = RequestMethod.GET)
    public List<SingleTaskVo> getAllSingleTask(@PathVariable String userId) {
        List<SingleTask> singleTasks = (List<SingleTask>) taskService.getUserSingleTask(userId);
        List<SingleTaskVo> singleTaskVos = new ArrayList<>();
        for (SingleTask s : singleTasks) {
            singleTaskVos.add(convertFactory().convert(SingleTaskVo.class, s));
        }
        return singleTaskVos;
    }

    /**
     * 显示用户所有未完成的独立task
     * @param userId
     * @return
     */
    @RequestMapping(value = "users/{userId}/Unfinished_single_task", method = RequestMethod.GET)
    public List<SingleTaskVo> getAllUnfinishedSingleTask(@PathVariable String userId) {
        List<SingleTask> singleTasks = (List<SingleTask>) taskService.getUserUnfinishedSingleTask(userId);
        List<SingleTaskVo> singleTaskVos = new ArrayList<>();
        for (SingleTask s : singleTasks) {
            singleTaskVos.add(convertFactory().convert(SingleTaskVo.class, s));
        }
        return singleTaskVos;
    }

    /**
     * 独立task归档
     * @param userId
     * @param taskId
     * @return
     */
    @RequestMapping(value = "users/{userId}/single_task/{taskId}", method = RequestMethod.DELETE)
    public SingleTaskVo deleteSingleTask(@PathVariable String userId, @PathVariable String taskId) {
        SingleTask singleTask = taskService.getById(taskId).orElseThrow(() -> new IllegalArgumentException("任务不存在!"));
        if (singleTask.getUserId() != Long.valueOf(userId)) {
            throw new IllegalArgumentException("任务不属于该用户!");
        }
        singleTask.setIsAchieved(Project.YesOrNo.YES);
        return convertFactory().convert(SingleTaskVo.class, taskService.save(singleTask));
    }

    /**
     * 独立task归档恢复
     * @param userId
     * @param taskId
     * @return
     */
    @RequestMapping(value = "users/{userId}/single_task/{taskId}", method = RequestMethod.PUT)
    public SingleTaskVo restoreSingleTask(@PathVariable String userId, @PathVariable String taskId) {
        SingleTask singleTask = taskService.getById(taskId).orElseThrow(() -> new IllegalArgumentException("任务不存在!"));
        if (singleTask.getUserId() != Long.valueOf(userId)) {
            throw new IllegalArgumentException("任务不属于该用户!");
        }
        singleTask.setIsAchieved(Project.YesOrNo.NO);
        return convertFactory().convert(SingleTaskVo.class, taskService.save(singleTask));
    }
}

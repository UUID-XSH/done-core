package info.xsh.done.core.controller;

import info.xsh.done.core.controller.vo.SingleTaskVo;
import info.xsh.done.core.controller.vo.TaskVo;
import info.xsh.done.core.domain.Project;
import info.xsh.done.core.domain.SingleTask;
import info.xsh.done.core.domain.Task;
import info.xsh.done.core.domain.User;
import info.xsh.done.core.exception.DoneProjectException;
import info.xsh.done.core.exception.ExceptionCode;
import info.xsh.done.core.service.ProjectService;
import info.xsh.done.core.service.TaskService;
import info.xsh.done.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private ProjectService projectService;

    //singleTask相关

    /**
     * 保存独立task
     *
     * @param singleTaskVo
     * @param userId
     * @return
     */
    @RequestMapping(value = "users/{userId}/tasks", method = RequestMethod.POST)
    public SingleTaskVo saveSingleTask(@RequestBody SingleTaskVo singleTaskVo, @PathVariable String userId) {
        SingleTask singleTask = convertFactory().convert(SingleTask.class, singleTaskVo);
        User user = userService.findById(userId).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "任务不存在!"));
        singleTask.setUserId(user.getId());
        singleTask = taskService.save(singleTask);
        log.info(String.format("Single task have been save: %s", singleTask.toString()));
        return convert(SingleTaskVo.class, singleTask);
    }

    /**
     * 更新singleTask
     *
     * @param singleTaskVo
     * @param userId
     * @param taskId
     * @return
     */
    @RequestMapping(value = "users/{userId}/single/{taskId}", method = RequestMethod.PUT)
    public SingleTaskVo update(@RequestBody SingleTaskVo singleTaskVo, @PathVariable String userId, @PathVariable String taskId) {
        SingleTask singleTask = taskService.getSingleTaskById(taskId).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "任务不存在!"));
        if (singleTask.getUserId() != Long.valueOf(userId)) {
            throw new DoneProjectException(ExceptionCode.NOT_FOUND, "任务不属于该用户!");
        }
        BeanUtils.copyProperties(singleTaskVo, singleTask, new String[]{"id", "isAchieved", "isFinal", "userId"});
        return convert(SingleTaskVo.class, taskService.save(singleTask));
    }

    /**
     * 查询指定独立task
     *
     * @param userId
     * @param taskId
     * @return
     */
    @RequestMapping(value = "users/{userId}/single/{taskId}", method = RequestMethod.GET)
    public SingleTaskVo getSingleTask(@PathVariable String userId, @PathVariable String taskId) {
        SingleTask singleTask = taskService.getSingleTaskById(taskId).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "任务不存在!"));
        if (singleTask.getUserId() != Long.valueOf(userId)) {
            throw new DoneProjectException(ExceptionCode.NOT_FOUND, "任务不属于该用户!");
        }
        return convert(SingleTaskVo.class, singleTask);
    }

    /**
     * 显示用户所有独立task
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "users/{userId}/single", method = RequestMethod.GET)
    public List<SingleTaskVo> getAllSingleTask(@PathVariable String userId) {
        List<SingleTask> singleTasks = (List<SingleTask>) taskService.getUserSingleTask(userId);
        List<SingleTaskVo> singleTaskVos = new ArrayList<>();
        for (SingleTask s : singleTasks) {
            singleTaskVos.add(convert(SingleTaskVo.class, s));
        }
        return singleTaskVos;
    }

    /**
     * 显示用户所有未完成的独立task
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "users/{userId}/Unfinished", method = RequestMethod.GET)
    public List<SingleTaskVo> getAllUnfinishedSingleTask(@PathVariable String userId) {
        List<SingleTask> singleTasks = (List<SingleTask>) taskService.getUserUnfinishedSingleTask(userId);
        List<SingleTaskVo> singleTaskVos = new ArrayList<>();
        for (SingleTask s : singleTasks) {
            singleTaskVos.add(convert(SingleTaskVo.class, s));
        }
        return singleTaskVos;
    }

    /**
     * 独立task归档
     *
     * @param userId
     * @param taskId
     * @return
     */
    @RequestMapping(value = "users/{userId}/single/{taskId}", method = RequestMethod.DELETE)
    public SingleTaskVo deleteSingleTask(@PathVariable String userId, @PathVariable String taskId) {
        SingleTask singleTask = taskService.getSingleTaskById(taskId).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "任务不存在!"));
        if (singleTask.getUserId() != Long.valueOf(userId)) {
            throw new DoneProjectException(ExceptionCode.NOT_FOUND, "任务不属于该用户!");
        }
        singleTask.setIsAchieved(Project.YesOrNo.YES);
        return convert(SingleTaskVo.class, taskService.save(singleTask));
    }

    /**
     * 独立task归档恢复
     *
     * @param userId
     * @param taskId
     * @return
     */
    @RequestMapping(value = "users/{userId}/single/{taskId}/restore", method = RequestMethod.PUT)
    public SingleTaskVo restoreSingleTask(@PathVariable String userId, @PathVariable String taskId) {
        SingleTask singleTask = taskService.getSingleTaskById(taskId).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "任务不存在!"));
        if (singleTask.getUserId() != Long.valueOf(userId)) {
            throw new DoneProjectException(ExceptionCode.NOT_FOUND, "任务不属于该用户!");
        }
        singleTask.setIsAchieved(Project.YesOrNo.NO);
        return convert(SingleTaskVo.class, taskService.save(singleTask));
    }

    //Task相关

    /**
     * 保存Task
     *
     * @param taskVo
     * @param projectId
     * @return
     */
    @RequestMapping(value = "projects/{projectId}/task", method = RequestMethod.POST)
    public TaskVo save(@RequestBody TaskVo taskVo, @PathVariable String projectId) {
        Task task = convertFactory().convert(Task.class, taskVo);
        Project project = projectService.findById(projectId).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "项目不存在！"));
        task.setProjectId(project.getId());
        task = taskService.save(task);
        log.info(String.format("Task have been save: %s", task.toString()));
        return convert(TaskVo.class, task);
    }

    /**
     * 更新Task
     *
     * @param taskVo
     * @param projectId
     * @param taskId
     * @return
     */
    @RequestMapping(value = "projects/{projectId}/task/{taskId}", method = RequestMethod.PUT)
    public TaskVo update(@RequestBody TaskVo taskVo, @PathVariable String projectId, @PathVariable String taskId) {
        Task task = taskService.getById(taskId).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "任务不存在！"));
        if (task.getProjectId() != Long.valueOf(projectId)) {
            throw new DoneProjectException(ExceptionCode.NOT_FOUND, "任务不属于该项目!");
        }
        BeanUtils.copyProperties(taskVo, task, new String[]{"id", "isAchieved", "isFinal", "projectId"});
        return convert(TaskVo.class, taskService.save(task));
    }

    /**
     * 获取指定task
     *
     * @param projectId
     * @param taskId
     * @return
     */
    @RequestMapping(value = "projects/{projectId}/task/{taskId}", method = RequestMethod.GET)
    public TaskVo get(@PathVariable String projectId, @PathVariable String taskId) {
        Task task = taskService.getById(taskId).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "任务不存在！"));
        if (task.getProjectId() != Long.valueOf(projectId)) {
            throw new DoneProjectException(ExceptionCode.NOT_FOUND, "任务不属于该项目!");
        }
        return convert(TaskVo.class, task);
    }

    /**
     * 指定project的全部task
     *
     * @param projectId
     * @return
     */
    @RequestMapping(value = "projects/{projectId}/task", method = RequestMethod.GET)
    public List<TaskVo> getAll(@PathVariable String projectId) {
        List<Task> tasks = taskService.getByPid(Long.valueOf(projectId));
        List<TaskVo> taskVos = new ArrayList<>();
        for (Task task : tasks) {
            taskVos.add(convert(TaskVo.class, task));
        }
        return taskVos;
    }

    /**
     * 指定project的全部未完成的task
     *
     * @param projectId
     * @return
     */
    @RequestMapping(value = "projects/{projectId}/unfinished", method = RequestMethod.GET)
    public List<TaskVo> getAllUnfinishedTask(@PathVariable String projectId) {
        List<Task> tasks = taskService.getUnfinishedTaskByPid(Long.valueOf(projectId));
        List<TaskVo> taskVos = new ArrayList<>();
        for (Task task : tasks) {
            taskVos.add(convert(TaskVo.class, task));
        }
        return taskVos;
    }

    /**
     * 归档任务
     *
     * @param projectId
     * @param taskId
     * @return
     */
    @RequestMapping(value = "projects/{projectId}/task/{taskId}", method = RequestMethod.DELETE)
    public TaskVo delete(@PathVariable String projectId, @PathVariable String taskId) {
        Task task = taskService.getById(taskId).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "任务不存在！"));
        if (task.getProjectId() != Long.valueOf(projectId)) {
            throw new DoneProjectException(ExceptionCode.NOT_FOUND, "任务不属于该项目!");
        }
        task.setIsAchieved(Project.YesOrNo.YES);
        return convert(TaskVo.class, taskService.save(task));
    }

    /**
     * 归档恢复
     *
     * @param projectId
     * @param taskId
     * @return
     */
    @RequestMapping(value = "projects/{projectId}/task/{taskId}/restore", method = RequestMethod.PUT)
    public TaskVo restore(@PathVariable String projectId, @PathVariable String taskId) {
        Task task = taskService.getById(taskId).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "任务不存在！"));
        if (task.getProjectId() != Long.valueOf(projectId)) {
            throw new DoneProjectException(ExceptionCode.NOT_FOUND, "任务不属于该项目!");
        }
        task.setIsAchieved(Project.YesOrNo.NO);
        return convert(TaskVo.class, taskService.save(task));
    }

    /**
     * 设置final层级
     *
     * @param projectId
     * @param taskId
     * @return
     */
    @RequestMapping(value = "projects/{projectId}/task/{taskId}/final", method = RequestMethod.PUT)
    public TaskVo setFinal(@PathVariable String projectId, @PathVariable String taskId) {
        Task task = taskService.getById(taskId).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "任务不存在！"));
        if (task.getProjectId() != Long.valueOf(projectId)) {
            throw new DoneProjectException(ExceptionCode.NOT_FOUND, "任务不属于该项目!");
        }
        task.setIsFinal(Project.YesOrNo.YES);
        return convert(TaskVo.class, taskService.save(task));
    }

}

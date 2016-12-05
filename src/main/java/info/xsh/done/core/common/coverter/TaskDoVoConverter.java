package info.xsh.done.core.common.coverter;

import info.xsh.done.core.controller.vo.SingleTaskVo;
import info.xsh.done.core.controller.vo.TaskVo;
import info.xsh.done.core.domain.SingleTask;
import info.xsh.done.core.domain.Task;
import info.yannxia.java.chameleon.annonation.Convertor;
import org.springframework.stereotype.Component;

/**
 * Created by xiaohuo on 16/11/30.
 */
@Component
public class TaskDoVoConverter {

    @Convertor
    public TaskVo doForward(Task task) {
        TaskVo taskVo = new TaskVo();
        taskVo.setId(task.getId());
        taskVo.setName(task.getName());
        taskVo.setDetail(task.getDetail());
        taskVo.setProjectId(task.getProjectId());
        taskVo.setIsAchieved(task.getIsAchieved());
        taskVo.setIsFinal(task.getIsFinal());
        return taskVo;

    }

    @Convertor
    public Task doBackward(TaskVo taskVo) {
        Task task = new Task();
        task.setName(taskVo.getName());
        task.setDetail(taskVo.getDetail());
        task.setProjectId(taskVo.getProjectId());
        return task;
    }

    @Convertor
    public SingleTaskVo SingleTaskDoForward(SingleTask singleTask) {
        SingleTaskVo singleTaskVo = new SingleTaskVo();
        singleTaskVo.setId(singleTask.getId());
        singleTaskVo.setName(singleTask.getName());
        singleTaskVo.setDetail(singleTask.getDetail());
        singleTaskVo.setUserId(singleTask.getUserId());
        singleTaskVo.setIsAchieved(singleTask.getIsAchieved());
        singleTaskVo.setIsFinal(singleTask.getIsFinal());
        return singleTaskVo;
    }

    @Convertor
    public SingleTask SingleTaskDoBackward(SingleTaskVo singleTaskVo) {
        SingleTask singleTask = new SingleTask();
        singleTask.setName(singleTaskVo.getName());
        singleTask.setDetail(singleTaskVo.getDetail());
        return singleTask;
    }
}

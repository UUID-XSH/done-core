package info.xsh.done.core.common.coverter;

import com.google.common.base.Converter;
import info.xsh.done.core.controller.vo.TaskVo;
import info.xsh.done.core.domain.Task;
import info.yannxia.java.chameleon.annonation.Convertor;
import org.springframework.stereotype.Component;

/**
 * Created by xiaohuo on 16/11/30.
 */
@Component
public class TaskDoVoConverter extends Converter<Task, TaskVo> {

    @Override
    @Convertor
    protected TaskVo doForward(Task task) {
        TaskVo taskVo = new TaskVo();
        taskVo.setId(task.getId());
        taskVo.setName(task.getName());
        taskVo.setDetail(task.getDetail());
        taskVo.setProjectId(task.getProjectId());
        return taskVo;

    }

    @Override
    @Convertor
    protected Task doBackward(TaskVo taskVo) {
        Task task = new Task();
        task.setName(taskVo.getName());
        task.setDetail(taskVo.getDetail());
        task.setProjectId(taskVo.getProjectId()); // TODO 为游离Task设置一个特殊的projectId
        return task;
    }
}

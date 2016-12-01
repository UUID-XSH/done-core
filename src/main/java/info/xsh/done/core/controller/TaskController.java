package info.xsh.done.core.controller;

import info.xsh.done.core.common.coverter.TaskDoVoConverter;
import info.xsh.done.core.controller.vo.TaskVo;
import info.xsh.done.core.domain.Task;
import info.xsh.done.core.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xiaohuo on 16/11/28.
 */
@RestController
@RequestMapping(value = "/api/v1.0", produces = "application/json")
@Slf4j
public class TaskController extends BaseController {

    @Autowired
    private TaskService taskService;

    private TaskDoVoConverter taskDoVoConverter = new TaskDoVoConverter();

    /**
     * @param taskVo
     * @return
     */
	@RequestMapping(value = "tasks", method = RequestMethod.POST)
	public TaskVo save(@RequestBody TaskVo taskVo) {
        Task task = taskService.save(taskDoVoConverter.reverse().convert(taskVo));
        log.info(String.format("Task have been save: %s",task.toString()));
        return taskDoVoConverter.convert(task);
	}

}

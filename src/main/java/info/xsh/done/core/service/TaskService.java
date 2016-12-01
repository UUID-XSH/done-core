package info.xsh.done.core.service;

import info.xsh.done.core.domain.Task;
import info.xsh.done.core.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiaohuo on 16/11/29.
 */
@Service
@Slf4j
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task save(Task task) {
        log.info(String.format("Task to be save: %s", task.toString()));
        return taskRepository.save(task);
    }

    public Iterable<Task> getAll() {
        return taskRepository.findAll();
    }

    /**
     * project id
     *
     * @param pid
     * @return
     */
    public List<Task> getByPid(long pid) {
        return taskRepository.findByProjectId(pid);
    }

}

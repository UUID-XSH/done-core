package info.xsh.done.core.service;

import info.xsh.done.core.domain.Project;
import info.xsh.done.core.domain.SingleTask;
import info.xsh.done.core.domain.Task;
import info.xsh.done.core.repository.SingleTaskRepository;
import info.xsh.done.core.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by xiaohuo on 16/11/29.
 */
@Service
@Slf4j
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SingleTaskRepository singleTaskRepository;

    public Task save(Task task) {
        log.info(String.format("Task to be save: %s", task.toString()));
        return taskRepository.save(task);
    }

    public SingleTask save(SingleTask singleTask) {
        log.info(String.format("Single task to be save: %s", singleTask.toString()));
        return singleTaskRepository.save(singleTask);
    }

    public Optional<SingleTask> getSingleTaskByUserIdAndTaskId(Long id, Long userId) {
        log.info(String.format("Single task to be find: taskId = %s", id));
        return Optional.ofNullable(singleTaskRepository.findByUserIdAndId(userId, id));
    }

    public Iterable<SingleTask> getUserSingleTask(String userId) {
        return singleTaskRepository.findByUserId(Long.valueOf(userId));
    }

    public Iterable<SingleTask> getUserUnfinishedSingleTask(String userId) {
        return singleTaskRepository.findByUserIdAndIsAchieved(Long.valueOf(userId), Project.YesOrNo.NO);
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

    public Optional<Task> getById(Long taskId) {
        log.info(String.format("Single task to be find: id = %s", taskId));
        return Optional.ofNullable(taskRepository.findOne(taskId));
    }

    public Optional<Task> getByIdAndProjectId(Long taskId,Long projectId) {
        return Optional.ofNullable(taskRepository.getByIdAndProjectId(taskId,projectId));
    }

    public List<Task> getUnfinishedTaskByPid(long pid) {
        return taskRepository.findByProjectIdAndIsAchieved(pid, Project.YesOrNo.NO);
    }


}

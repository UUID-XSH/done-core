package info.xsh.done.core.service;

import info.xsh.done.core.domain.SingleTask;
import info.xsh.done.core.domain.Task;
import info.xsh.done.core.domain.YesOrNo;
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
        log.info("Task to be save: {}", task.toString());
        return taskRepository.save(task);
    }

    public SingleTask save(SingleTask singleTask) {
        log.info("Single task to be save: {}", singleTask.toString());
        return singleTaskRepository.save(singleTask);
    }

    public Optional<SingleTask> getSingleTaskByUserIdAndTaskId(Long id, Long userId) {
        log.info("Single task to be find: taskId = {}", id);
        return Optional.ofNullable(singleTaskRepository.findByUserIdAndId(userId, id));
    }

    public Iterable<SingleTask> getUserSingleTask(String userId) {
        return singleTaskRepository.findByUserId(Long.valueOf(userId));
    }

    public Iterable<SingleTask> getUserUnfinishedSingleTask(String userId) {
        return singleTaskRepository.findByUserIdAndIsAchieved(Long.valueOf(userId), YesOrNo.NO);
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
        log.info("Single task to be find: id = {}", taskId);
        return Optional.ofNullable(taskRepository.findOne(taskId));
    }

    public Optional<Task> getByIdAndProjectId(Long taskId, Long projectId) {
        return Optional.ofNullable(taskRepository.getByIdAndProjectId(taskId, projectId));
    }

    public List<Task> getUnfinishedTaskByPid(Long pid) {
        return taskRepository.findByProjectIdAndIsAchieved(pid, YesOrNo.NO);
    }

    public List<Task> findByUserIdAndProjectIdAndIsAchieved(Long userId,Long pid) {
        return taskRepository.findByUserIdAndProjectIdAndIsAchieved(userId,pid, YesOrNo.NO.toString());
    }

    public List<Task> getByUserIdAndProjectId(Long userId, Long projectId) {
        return taskRepository.findByUserIdAndProjectId(userId, projectId);
    }

    public Optional<Task> findByUserIdAndProjectIdAndTaskId(Long userId, Long projectId, Long taskId) {
        return Optional.ofNullable(taskRepository.findByUserIdAndProjectIdAndTaskId(userId, projectId, taskId));
    }
}

package info.xsh.done.core.repository;

import info.xsh.done.core.domain.Task;
import info.xsh.done.core.domain.YesOrNo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiaohuo on 16/11/29.
 */
@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findByProjectId(Long projectId);

    Task getByIdAndProjectId(Long id, Long projectId);

    List<Task> findByProjectIdAndIsAchieved(Long projectId, YesOrNo isAchieved);

    @Query(value = "select t.* from task t join project p on t.project_id = p.id where p.user_id = ?1 and p.id = ?2", nativeQuery = true)
    List<Task> findByUserIdAndProjectId(Long userId, Long projectId);

    @Query(value = "select t.* from task t join project p on t.project_id = p.id where p.user_id = ?1 and p.id = ?2 and t.id = ?3", nativeQuery = true)
    Task findByUserIdAndProjectIdAndTaskId(Long userId, Long projectId, Long taskId);

    @Query(value = "select t.* from task t join project p on t.project_id = p.id where p.user_id = ?1 and p.id = ?2 and t.is_achieved = ?3", nativeQuery = true)
    List<Task> findByUserIdAndProjectIdAndIsAchieved(Long userId, Long projectId, String isAchieved);

}

package info.xsh.done.core.repository;

import info.xsh.done.core.domain.Task;
import info.xsh.done.core.domain.YesOrNo;
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
}

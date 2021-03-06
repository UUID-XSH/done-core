package info.xsh.done.core.repository;

import info.xsh.done.core.domain.TaskTag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by xiaohuo on 16/12/7.
 */
public interface TaskTagRepository extends CrudRepository<TaskTag, Long> {
    List<TaskTag> findByTaskId(long taskId);

    Optional<TaskTag> findByTaskIdAndTagId(long taskId, long tagId);
}

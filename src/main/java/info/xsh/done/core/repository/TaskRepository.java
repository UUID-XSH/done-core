package info.xsh.done.core.repository;

import info.xsh.done.core.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by xiaohuo on 16/11/29.
 */
@Repository
public interface TaskRepository extends CrudRepository<Task,Long> {
}

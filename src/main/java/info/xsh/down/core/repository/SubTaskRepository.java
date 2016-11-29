package info.xsh.down.core.repository;

import info.xsh.down.core.domain.SubTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by xiaohuo on 16/11/29.
 */
@Repository
public interface SubTaskRepository extends CrudRepository<SubTask,Long> {
}

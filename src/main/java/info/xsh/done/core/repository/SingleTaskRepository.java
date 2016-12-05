package info.xsh.done.core.repository;

import info.xsh.done.core.domain.Project;
import info.xsh.done.core.domain.SingleTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yangxueying on 2016/12/3.
 */
@Repository
public interface SingleTaskRepository extends CrudRepository<SingleTask, Long> {
    List<SingleTask> findByUserId(long userId);
    List<SingleTask> findByUserIdAndIsAchieved(long userId,Project.YesOrNo isAchieved);
}

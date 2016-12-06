package info.xsh.done.core.repository;

import info.xsh.done.core.domain.Timer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yangxueying on 2016/12/6.
 */
@Repository
public interface TimerRepository extends CrudRepository<Timer, Long> {

    Timer findByTaskId(Long taskId);

    @Query(value = "select * from tomato join (select t.* from task t join project p on t.project_id = p.id where p.user_id = ?1 and p.id = ?2 and t.id = ?3) t on tomato.id = t.id", nativeQuery = true)
    Timer findByUserIdAndProjectIdAndTaskId(Long userId, Long projectId, Long taskId);
}

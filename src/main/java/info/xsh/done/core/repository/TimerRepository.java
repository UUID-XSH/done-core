package info.xsh.done.core.repository;

import info.xsh.done.core.domain.Timer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yangxueying on 2016/12/6.
 */
@Repository
public interface TimerRepository extends CrudRepository<Timer, Long> {

    Timer findByTaskId(Long taskId);
}

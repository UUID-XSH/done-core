package info.xsh.done.core.service;

import info.xsh.done.core.domain.Timer;
import info.xsh.done.core.repository.TimerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by yangxueying on 2016/12/6.
 */
@Service
@Slf4j
public class TimerService {

    @Autowired
    private TimerRepository timerRepository;

    public Timer save(Timer timer) {
        return timerRepository.save(timer);
    }

    public Optional<Timer> findByTaskId(Long taskId) {
        return Optional.ofNullable(timerRepository.findByTaskId(taskId));
    }

    public Optional<Timer> findByUserIdAndProjectIdAndTaskId(Long userId,Long projectId,Long taskId) {
        return Optional.ofNullable(timerRepository.findByUserIdAndProjectIdAndTaskId(userId,projectId,taskId));
    }


}

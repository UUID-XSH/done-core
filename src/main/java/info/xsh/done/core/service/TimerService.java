package info.xsh.done.core.service;

import info.xsh.done.core.repository.TimerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yangxueying on 2016/12/6.
 */
@Service
@Slf4j
public class TimerService {

    @Autowired
    private TimerRepository timerRepository;



}

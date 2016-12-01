package info.xsh.done.core.service;

import info.xsh.done.core.domain.User;
import info.xsh.done.core.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by xiaohuo on 16/11/29.
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        log.info(String.format("save entity: %s", user.toString()));
        return userRepository.save(user);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(String id) {
        return Optional.ofNullable(userRepository.findOne(Long.valueOf(id)));
    }

    public void delete(String id) {
        userRepository.delete(Long.valueOf(id));
    }

}
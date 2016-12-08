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
        log.info("save entity: {}", user.toString());
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    public void delete(Long id) {
        userRepository.delete(Long.valueOf(id));
    }

    public Optional<User> findByName(String name) {
        return Optional.ofNullable(userRepository.findByName(name));
    }

    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

}
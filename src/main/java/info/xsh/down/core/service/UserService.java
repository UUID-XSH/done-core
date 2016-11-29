package info.xsh.down.core.service;

import info.xsh.down.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiaohuo on 16/11/29.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
}

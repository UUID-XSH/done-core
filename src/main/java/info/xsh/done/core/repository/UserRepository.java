package info.xsh.done.core.repository;

import info.xsh.done.core.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by xiaohuo on 16/11/29.
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    User findByName(String name);
}

package info.xsh.done.core.repository;

import info.xsh.done.core.domain.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by xiaohuo on 16/12/7.
 */
public interface TagRepository extends CrudRepository<Tag, Long> {

    Optional<Tag> findByUserIdAndName(long userId, String name);

    Optional<Tag> findByIdAndUserId(long id, long userId);

}

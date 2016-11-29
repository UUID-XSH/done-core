package info.xsh.done.core.repository;

import info.xsh.done.core.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by xiaohuo on 16/11/29.
 */
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
	Optional<Project> findById();
}

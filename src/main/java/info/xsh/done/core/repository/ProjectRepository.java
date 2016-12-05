package info.xsh.done.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import info.xsh.done.core.domain.Project;

import java.util.List;

/**
 * Created by xiaohuo on 16/11/29.
 */
@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
    List<Project> findByUserIdAndIsAchievedAndIsArchived(Long userId, Project.YesOrNo isAchieved, Project.YesOrNo isArchived);

    Project findByIdAndUserId(Long id, Long userId);
}

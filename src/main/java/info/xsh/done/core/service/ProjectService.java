package info.xsh.done.core.service;

import info.xsh.done.core.domain.Project;
import info.xsh.done.core.domain.YesOrNo;
import info.xsh.done.core.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by xiaohuo on 16/11/29.
 */
@Service
@Slf4j
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project save(Project project) {
        log.info("save entity: {}", project.toString());


        return projectRepository.save(project);
    }


    public Iterable<Project> findAll() {
        return projectRepository.findAll();
    }

    public List<Project> findByUserId(long userId) {
        return projectRepository.findByUserIdAndIsAchievedAndIsArchived(userId, YesOrNo.NO, YesOrNo.NO);
    }


    public Optional<Project> findById(String id) {
        return Optional.ofNullable(projectRepository.findOne(Long.valueOf(id)));
    }


}

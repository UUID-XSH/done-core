package info.xsh.done.core.service;

import info.xsh.done.core.domain.Project;
import info.xsh.done.core.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiaohuo on 16/11/29.
 */
@Service
@Slf4j
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public Project add(Project project) {

		log.info("11111");

		return projectRepository.save(project);
	}
}

package info.xsh.done.core.service;

import info.xsh.done.core.domain.Project;
import info.xsh.done.core.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiaohuo on 16/11/29.
 */
@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;

	public void add(Project project) {
		projectRepository.save(project);
	}
}
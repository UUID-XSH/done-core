package info.xsh.done.core.common.coverter;

import com.google.common.base.Converter;
import info.xsh.done.core.controller.vo.ProjectVo;
import info.xsh.done.core.domain.Project;
import org.springframework.stereotype.Component;

/**
 * Created by xiaohuo on 16/11/29.
 */
public class ProjectDoVoConverter extends Converter<Project, ProjectVo> {

	@Override
	protected ProjectVo doForward(Project project) {
		return null;
	}

	@Override
	protected Project doBackward(ProjectVo projectVo) {
		Project project = new Project();
		project.setName(projectVo.getName());
		project.setUserId(projectVo.getUserId());
		return project;
	}
}

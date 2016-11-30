package info.xsh.done.core.common.coverter;

import com.google.common.base.Converter;

import info.xsh.done.core.controller.vo.ProjectVo;
import info.xsh.done.core.domain.Project;

/**
 * Created by xiaohuo on 16/11/29.
 */
public class ProjectDoVoConverter extends Converter<Project, ProjectVo> {

	@Override
	protected ProjectVo doForward(Project project) {
		ProjectVo projectVo = new ProjectVo();
		projectVo.setId(project.getId());
		projectVo.setName(project.getName());
		projectVo.setCreateTime(project.getCreateTime());
		projectVo.setIsAchieved(project.getIsAchieved());
		projectVo.setIsArchived(project.getIsArchived());
		projectVo.setUserId(project.getUserId());
		return projectVo;
	}

	@Override
	protected Project doBackward(ProjectVo projectVo) {
		Project project = new Project();
		project.setName(projectVo.getName());
		project.setUserId(projectVo.getUserId());
		return project;
	}

}

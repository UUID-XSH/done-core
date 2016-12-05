package info.xsh.done.core.common.coverter;

import info.xsh.done.core.controller.vo.ProjectVo;
import info.xsh.done.core.domain.Project;
import info.yannxia.java.chameleon.annonation.Convertor;
import org.springframework.stereotype.Component;

/**
 * Created by xiaohuo on 16/11/29.
 */
@Component
public class ProjectDoVoConverter {

    @Convertor
    public ProjectVo doForward(Project project) {
        ProjectVo projectVo = new ProjectVo();
        projectVo.setId(project.getId());
        projectVo.setName(project.getName());
        projectVo.setCreateTime(project.getCreateTime());
        projectVo.setIsAchieved(project.getIsAchieved());
        projectVo.setIsArchived(project.getIsArchived());
        projectVo.setUserId(project.getUserId());
        return projectVo;
    }

    @Convertor
    public Project doBackward(ProjectVo projectVo) {
        Project project = new Project();
        project.setName(projectVo.getName());
        project.setUserId(projectVo.getUserId());
        return project;
    }

}

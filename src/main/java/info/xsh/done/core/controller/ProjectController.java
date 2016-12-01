package info.xsh.done.core.controller;

import info.xsh.done.core.common.coverter.ProjectDoVoConverter;
import info.xsh.done.core.common.coverter.TaskDoVoConverter;
import info.xsh.done.core.controller.vo.ProjectVo;
import info.xsh.done.core.controller.vo.TaskVo;
import info.xsh.done.core.domain.Project;
import info.xsh.done.core.domain.Task;
import info.xsh.done.core.service.ProjectService;
import info.xsh.done.core.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xiaohuo on 16/11/28.
 */
@RestController
@RequestMapping(value = "/api/v1.0", produces = "application/json")
@Slf4j
public class ProjectController extends BaseController {

    private ProjectDoVoConverter projectDoVoConverter = new ProjectDoVoConverter();

    private TaskDoVoConverter taskDoVoConverter = new TaskDoVoConverter();

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskService taskService;

    /**
     * 添加项目
     *
     * @param userId
     * @param projectVo{"name" = "project name"}
     * @return
     */
    @RequestMapping(value = "users/{user_id}/projects", method = RequestMethod.POST)
    public ProjectVo add(@PathVariable("user_id") String userId, @RequestBody ProjectVo projectVo) {
        projectVo.setUserId(Long.valueOf(userId));
        Project project = projectDoVoConverter.reverse().convert(projectVo);
        return projectDoVoConverter.convert(projectService.save(project));
    }

    /**
     * 获取该用户所有正在进行的项目
     * 不包括已归档和已完成
     * 暂不加载Task信息
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "users/{user_id}/projects", method = RequestMethod.GET)
    public List<ProjectVo> getAll(@PathVariable("user_id") String userId) {
        List<Project> projects = projectService.findByUserId(Long.valueOf(userId));
        return projects.stream().map(project -> projectDoVoConverter.convert(project)).collect(Collectors.toList());
    }

    /**
     * 获取单个项目
     * 带Task详情
     *
     * @param id
     */
    @RequestMapping(value = "users/{user_id}/projects/{id}", method = RequestMethod.GET)
    public ProjectVo get(@PathVariable("user_id") String userId, @PathVariable String id) {
        Project project = projectService.findById(id).orElseThrow(() -> new IllegalArgumentException("项目不存在"));
        if (project.getUserId() != Long.valueOf(userId)) {
            throw new IllegalArgumentException("项目不属于该用户");
        }
        ProjectVo projectVo = projectDoVoConverter.convert(project);
        List<Task> tasks = taskService.getByPid(project.getId());
        List<TaskVo> taskVos = tasks.stream().map(task -> taskDoVoConverter.convert(task)).collect(Collectors.toList());
        projectVo.setTaskVos(taskVos);
        return projectVo;
    }

    /**
     * 归档单个项目
     *
     * @param userId 用户id
     * @param id     项目id
     * @return
     */
    @RequestMapping(value = "users/{user_id}/projects/{id}", method = RequestMethod.DELETE)
    public ProjectVo delete(@PathVariable("user_id") String userId, @PathVariable String id) {
        Project project = projectService.findById(id).orElseThrow(() -> new RuntimeException("项目不存在"));
        if (project.getUserId() != Long.valueOf(userId)) {
            throw new IllegalArgumentException("项目不属于该用户");
        }
        project.setIsArchived(Project.YesOrNo.YES);
        return projectDoVoConverter.convert(projectService.save(project));
    }

    /**
     * 恢复单个项目
     *
     * @param userId
     * @param id
     * @return
     */
    @RequestMapping(value = "users/{user_id}/projects/{id}", method = RequestMethod.PUT)
    public ProjectVo restore(@PathVariable("user_id") String userId, @PathVariable String id) {
        Project project = projectService.findById(id).orElseThrow(() -> new RuntimeException("项目不存在"));
        if (project.getUserId() != Long.valueOf(userId)) {
            throw new IllegalArgumentException("项目不属于该用户");
        }
        project.setIsArchived(Project.YesOrNo.NO);
        return projectDoVoConverter.convert(projectService.save(project));
    }

}

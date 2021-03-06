package info.xsh.done.core.controller;

import info.xsh.done.core.controller.vo.ProjectVo;
import info.xsh.done.core.controller.vo.TaskVo;
import info.xsh.done.core.domain.Project;
import info.xsh.done.core.domain.Task;
import info.xsh.done.core.domain.YesOrNo;
import info.xsh.done.core.exception.DoneProjectException;
import info.xsh.done.core.exception.ExceptionCode;
import info.xsh.done.core.service.ProjectService;
import info.xsh.done.core.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "users/{userId}/projects", method = RequestMethod.POST)
    public ProjectVo add(@PathVariable Long userId, @RequestBody ProjectVo projectVo) {
        projectVo.setUserId(userId);
        Project project = convert(Project.class, projectVo);
        return convert(ProjectVo.class, projectService.save(project));
    }

    /**
     * 获取该用户所有正在进行的项目
     * 不包括已归档和已完成
     * 暂不加载Task信息
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "users/{userId}/projects", method = RequestMethod.GET)
    public List<ProjectVo> getAll(@PathVariable Long userId) {
        List<Project> projects = projectService.findByUserId(userId);
        return projects.stream().map(project -> convert(ProjectVo.class, project)).collect(Collectors.toList());
    }

    /**
     * 更新指定项目名
     *
     * @param userId
     * @param projectVo
     * @return
     */
    @RequestMapping(value = "users/{userId}/projects/{id}", method = RequestMethod.PUT)
    public ProjectVo update(@PathVariable Long userId, @PathVariable Long id, @RequestBody ProjectVo projectVo) {
        Project project = projectService.findByProjectIdAndUserId(id, userId).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "项目不存在！"));
        project.setName(projectVo.getName());
        return convert(ProjectVo.class, projectService.save(project));
    }

    /**
     * 获取单个项目
     * 带Task详情
     *
     * @param userId
     * @param id
     * @return
     */
    @RequestMapping(value = "users/{userId}/projects/{id}", method = RequestMethod.GET)
    public ProjectVo get(@PathVariable Long userId, @PathVariable Long id) {
        Project project = projectService.findByProjectIdAndUserId(id, userId).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "项目不存在！"));
        ProjectVo projectVo = convert(ProjectVo.class, project);
        List<Task> tasks = taskService.getByPid(project.getId());
        List<TaskVo> taskVos = tasks.stream().map(task -> convert(TaskVo.class, task)).collect(Collectors.toList());
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
    @RequestMapping(value = "users/{userId}/projects/{id}", method = RequestMethod.DELETE)
    public ProjectVo delete(@PathVariable Long userId, @PathVariable Long id) {
        Project project = projectService.findByProjectIdAndUserId(id, userId).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "项目不存在！"));
        project.setIsArchived(YesOrNo.YES);
        return convert(ProjectVo.class, projectService.save(project));
    }

    /**
     * 恢复单个项目
     *
     * @param userId
     * @param id
     * @return
     */
    @RequestMapping(value = "users/{userId}/projects/{id}/restore", method = RequestMethod.PUT)
    public ProjectVo restore(@PathVariable Long userId, @PathVariable Long id) {
        Project project = projectService.findByProjectIdAndUserId(id, userId).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "项目不存在！"));
        project.setIsArchived(YesOrNo.NO);
        return convert(ProjectVo.class, projectService.save(project));
    }

}

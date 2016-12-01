package info.xsh.done.core.controller;

import info.xsh.done.core.common.coverter.ProjectDoVoConverter;
import info.xsh.done.core.common.coverter.TaskDoVoConverter;
import info.xsh.done.core.controller.vo.ProjectVo;
import info.xsh.done.core.controller.vo.ResponseVo;
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
    public ResponseVo add(@PathVariable("user_id") String userId, @RequestBody ProjectVo projectVo) {
        ResponseVo responseVo =new ResponseVo();
        projectVo.setUserId(Long.valueOf(userId));
        Project project = projectDoVoConverter.reverse().convert(projectVo);
        responseVo.setCode(200);
        responseVo.setRes(projectDoVoConverter.convert(projectService.save(project)));
        return responseVo;
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
    public ResponseVo getAll(@PathVariable("user_id") String userId) {
        ResponseVo responseVo =new ResponseVo();
        List<Project> projects = projectService.findByUserId(Long.valueOf(userId));
        responseVo.setCode(200);
        responseVo.setRes(projects.stream().map(project -> projectDoVoConverter.convert(project)).collect(Collectors.toList()));
        return responseVo;
    }

    /**
     * 获取单个项目
     * 带Task详情
     *
     * @param id
     */
    @RequestMapping(value = "users/{user_id}/projects/{id}", method = RequestMethod.GET)
    public ResponseVo get(@PathVariable("user_id") String userId, @PathVariable String id) {
        ResponseVo responseVo =new ResponseVo();
        Project project = projectService.findById(id).orElseThrow(() -> new IllegalArgumentException("项目不存在"));
        if (project.getUserId() != Long.valueOf(userId)) {
            throw new IllegalArgumentException("项目不属于该用户");
        }
        ProjectVo projectVo = projectDoVoConverter.convert(project);
        List<Task> tasks = taskService.getByPid(project.getId());
        List<TaskVo> taskVos = tasks.stream().map(task -> taskDoVoConverter.convert(task)).collect(Collectors.toList());
        projectVo.setTaskVos(taskVos);
        responseVo.setCode(200);
        responseVo.setRes(projectVo);
        return responseVo;
    }

    /**
     * 归档单个项目
     *
     * @param userId 用户id
     * @param id     项目id
     * @return
     */
    @RequestMapping(value = "users/{user_id}/projects/{id}", method = RequestMethod.DELETE)
    public ResponseVo delete(@PathVariable("user_id") String userId, @PathVariable String id) {
        ResponseVo responseVo =new ResponseVo();
        Project project = projectService.findById(id).orElseThrow(() -> new RuntimeException("项目不存在"));
        if (project.getUserId() != Long.valueOf(userId)) {
            throw new IllegalArgumentException("项目不属于该用户");
        }
        project.setIsArchived(Project.YesOrNo.YES);
        responseVo.setCode(200);
        responseVo.setRes(projectDoVoConverter.convert(projectService.save(project)));
        return responseVo;
    }

    /**
     * 恢复单个项目
     *
     * @param userId
     * @param id
     * @return
     */
    @RequestMapping(value = "users/{user_id}/projects/{id}", method = RequestMethod.PUT)
    public ResponseVo restore(@PathVariable("user_id") String userId, @PathVariable String id) {
        ResponseVo responseVo =new ResponseVo();
        Project project = projectService.findById(id).orElseThrow(() -> new RuntimeException("项目不存在"));
        if (project.getUserId() != Long.valueOf(userId)) {
            throw new IllegalArgumentException("项目不属于该用户");
        }
        project.setIsArchived(Project.YesOrNo.NO);
        responseVo.setCode(200);
        responseVo.setRes(projectDoVoConverter.convert(projectService.save(project)));
        return responseVo;
    }

}

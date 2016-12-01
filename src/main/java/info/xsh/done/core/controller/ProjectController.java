package info.xsh.done.core.controller;

import info.xsh.done.core.common.coverter.TaskDoVoConverter;
import info.xsh.done.core.controller.vo.TaskVo;
import info.xsh.done.core.domain.Task;
import info.xsh.done.core.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import info.xsh.done.core.common.coverter.ProjectDoVoConverter;
import info.xsh.done.core.controller.vo.ProjectVo;
import info.xsh.done.core.domain.Project;
import info.xsh.done.core.service.ProjectService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by xiaohuo on 16/11/28.
 *
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
	 */
	@RequestMapping(value = "users/{user_id}/projects", method = RequestMethod.POST)
	public Project add(@RequestBody ProjectVo projectVo) {
		Project project = projectDoVoConverter.reverse().convert(projectVo);
		return projectService.save(project);

	}

	/**
	 * 获取所有项目
	 * 不带Task详情
	 */
	@RequestMapping(value = "users/{user_id}/projects/", method = RequestMethod.GET)
	public Iterable<Project> getAll() {
		return projectService.findAll();
	}

	/**
	 * 获取单个项目
	 * 带Task详情
	 * @param id
	 */
	@RequestMapping(value = "users/{user_id}/projects/{id}", method = RequestMethod.GET)
	public ProjectVo get(@PathVariable String id) {
		Project project = projectService.findById(id).orElseThrow(() -> new IllegalArgumentException("项目不存在"));
		ProjectVo projectVo = projectDoVoConverter.convert(project);
		List<Task> tasks = taskService.getByPid(project.getId());
		List<TaskVo> taskVos = tasks.stream().map(task -> taskDoVoConverter.convert(task)).collect(Collectors.toList());
		projectVo.setTaskVos(taskVos);
		return projectVo;

	}

	/**
	 * 归档单个项目
	 * 
	 * @param id
	 */
	@RequestMapping(value = "users/{user_id}/projects/{id}", method = RequestMethod.DELETE)
	public Project delete(@PathVariable String id) {
		Project project = projectService.findById(id).orElseThrow(() -> new RuntimeException("项目不存在"));
		project.setIsArchived(Project.YesOrNo.YES);
		return projectService.save(project);
	}

	/**
	 * 恢复单个项目
	 */
	@RequestMapping(value = "users/{user_id}/projects/{id}", method = RequestMethod.PUT)
	public Project restore(@PathVariable String id) {
		Project project = projectService.findById(id).orElseThrow(() -> new RuntimeException("项目不存在"));
		project.setIsArchived(Project.YesOrNo.NO);
		return projectService.save(project);
	}

}

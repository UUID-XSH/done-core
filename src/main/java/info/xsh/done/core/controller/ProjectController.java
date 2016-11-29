package info.xsh.done.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import info.xsh.done.core.common.coverter.ProjectDoVoConverter;
import info.xsh.done.core.controller.vo.ProjectVo;
import info.xsh.done.core.domain.Project;
import info.xsh.done.core.service.ProjectService;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by xiaohuo on 16/11/28.
 *
 */
@RestController
@RequestMapping(value = "/api/v1.0", produces = "application/json")
@Slf4j
public class ProjectController extends BaseController {

	private ProjectDoVoConverter projectDoVoConverter = new ProjectDoVoConverter();

	@Autowired
	private ProjectService projectService;

	/**
	 * 添加项目
	 */
	@RequestMapping(value = "/projects", method = RequestMethod.POST)
	public Project add(@RequestBody ProjectVo projectVo) {
		Project project = projectDoVoConverter.reverse().convert(projectVo);
		return projectService.add(project);

	}

	/**
	 * 获取所有项目
	 */
	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public void getAll() {
		log.info("ok");
	}

	/**
	 * 获取单个项目
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
	public void get(@PathVariable String id) {

	}

	/**
	 * 归档单个项目
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/projects/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String id) {

	}

	/**
	 * 恢复单个项目
	 */
	@RequestMapping(value = "projects/{id}", method = RequestMethod.PUT)
	public void restore(@PathVariable String id) {

	}

}

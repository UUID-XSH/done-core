package info.xsh.done.core.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import info.xsh.done.core.DoneApplication;
import info.xsh.done.core.domain.Project;

/**
 * Created by xiaohuo on 16/11/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DoneApplication.class, properties = "application.properties")
public class ProjectServiceTest {

	private Project project;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private UserService userService;

	@Autowired
	private ConversionService conversionService;

	@Before
	public void setup() {
		project = new Project();
		project.setId(1L);
		project.setName("CPP学习");
	}

	@Test
	public void add() {
		projectService.save(project);
	}
}

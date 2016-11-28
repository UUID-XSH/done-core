package info.xsh.down.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xiaohuo on 16/11/28.
 */
@RestController
@RequestMapping("/api/v1.0")
public class ProjectController extends BaseController {

    @RequestMapping(value = "/projects",method = RequestMethod.POST,produces = "application/json")
	public void create() {

	}

	@RequestMapping(value = "/projects",method = RequestMethod.GET,produces = "application/json")
	public void get(){

    }
}

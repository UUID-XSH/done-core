package info.xsh.done.core.controller;

import info.xsh.done.core.controller.vo.SingleTaskVo;
import info.xsh.done.core.domain.SingleTask;
import info.xsh.done.core.domain.User;
import info.xsh.done.core.service.TaskService;
import info.xsh.done.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xiaohuo on 16/11/28.
 */
@RestController
@RequestMapping(value = "/api/v1.0", produces = "application/json")
@Slf4j
public class TaskController extends BaseController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    /**
     * 想法盒子 单纯记录tasks
     * @param singleTaskVo
     * @param user_id
     * @return
     */
	@RequestMapping(value = "tasks/{user_id}", method = RequestMethod.POST)
	public SingleTaskVo save(@RequestBody SingleTaskVo singleTaskVo, @PathVariable String user_id) {
        SingleTask singleTask = convertFactory().convert(SingleTask.class,singleTaskVo);
        User user=userService.findById(user_id).orElseThrow(() -> new IllegalArgumentException("用户不存在"));
        singleTask.setUserId(user.getId());
        singleTask=taskService.save(singleTask);
        log.info(String.format("Single task have been save: %s",singleTask.toString()));
        return convertFactory().convert(SingleTaskVo.class,singleTask);
	}





}

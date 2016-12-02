package info.xsh.done.core.controller;

import info.xsh.done.core.controller.vo.ResponseVo;
import info.xsh.done.core.controller.vo.UserVo;
import info.xsh.done.core.domain.Project;
import info.xsh.done.core.domain.User;
import info.xsh.done.core.service.ProjectService;
import info.xsh.done.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * author : misha
 */
@RestController
@RequestMapping(value = "/api/v1.0", produces = "application/json")
@Slf4j
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    /**
     * @param userVo
     * @return
     */
    @RequestMapping(value = "users", method = RequestMethod.POST)
    public ResponseVo create(@RequestBody UserVo userVo) {
        ResponseVo responseVo =new ResponseVo();
        User user = convertFactory().convert(User.class, userVo);
        Project project =new Project();
        try {
            user=userService.save(user);
            project.setUserId(user.getId());
            project.setName("user"+user.getId()+"DefaultProject");
            projectService.save(project);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new RuntimeException("创建用户失败！");
        }
        responseVo.setCode(200);
        responseVo.setRes(convertFactory().convert(UserVo.class,user));
        return responseVo;
    }

    /**查询所有用户
     * @return
     */
    @RequestMapping(value = "users", method = RequestMethod.GET)
    public ResponseVo get() {
        ResponseVo responseVo =new ResponseVo();
        List<UserVo> userVos = new ArrayList<>();
        Iterable<User> users = userService.findAll();
        for (User user : users) {
            userVos.add(convertFactory().convert(UserVo.class,user));
        }
        responseVo.setCode(200);
        responseVo.setRes(userVos);
        return responseVo;
    }

    /**按照ID查询用户
     * @param user_id
     * @return
     */
    @RequestMapping(value = "users/{user_id}", method = RequestMethod.GET)
    public ResponseVo getById(@PathVariable String user_id) {
        ResponseVo responseVo =new ResponseVo();
        User user = userService.findById(user_id).orElseThrow(() -> new IllegalArgumentException("用户不存在"));
        responseVo.setCode(200);
        responseVo.setRes(convertFactory().convert(UserVo.class,user));
        return responseVo;
    }

    /**
     * 按照用户名查询
     * @param user_name
     * @return
     */
    @RequestMapping(value = "users/name/{user_name}", method = RequestMethod.GET)
    public ResponseVo getByName(@PathVariable String user_name) {
        ResponseVo responseVo =new ResponseVo();
        User user = userService.findByName(user_name).orElseThrow(() -> new IllegalArgumentException("用户不存在"));
        responseVo.setCode(200);
        responseVo.setRes(convertFactory().convert(UserVo.class,user));
        return responseVo;
    }

    /**
     * 更新
     *
     * @param user_id
     * @param userVo
     * @return
     */
    @RequestMapping(value = "users/{user_id}", method = RequestMethod.PUT)
    public ResponseVo update(@PathVariable String user_id, @RequestBody UserVo userVo) {
        ResponseVo responseVo =new ResponseVo();
        User user = userService.findById(user_id).orElseThrow(() -> new IllegalArgumentException("用户不存在"));
        BeanUtils.copyProperties(userVo, user, new String[]{"id"});
        responseVo.setCode(200);
        responseVo.setRes(convertFactory().convert(UserVo.class,userService.save(user)));
        return responseVo;
    }

    /**
     * 删除
     * @param user_id
     * @return
     */
    @RequestMapping(value = "users/{user_id}", method = RequestMethod.DELETE)
    public ResponseVo delete(@PathVariable String user_id) {
        ResponseVo responseVo =new ResponseVo();
        try {
            userService.delete(user_id);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new RuntimeException("删除用户失败！");
        }
        responseVo.setCode(200);
        responseVo.setRes("用户ID："+user_id+"，删除成功！");
        return responseVo;
    }
}

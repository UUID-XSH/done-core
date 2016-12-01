package info.xsh.done.core.controller;

import info.xsh.done.core.controller.vo.UserVo;
import info.xsh.done.core.domain.User;
import info.xsh.done.core.service.ProjectService;
import info.xsh.done.core.service.UserService;
import info.yannxia.java.chameleon.ConvertFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xiaohuo on 16/12/1.
 */
@RestController
@RequestMapping(value = "/api/v1.0", produces = "application/json")
@Slf4j
public class UserController extends BaseController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;


    /**
     * @param userVo
     * @return
     */
    @RequestMapping(value = "users/create", method = RequestMethod.POST)
    public User create(@RequestBody UserVo userVo) {
        User user = convertFactory().convert(User.class, userVo);
        return userService.save(user);
    }

    /**
     * @return
     */
    @RequestMapping(value = "users/all", method = RequestMethod.GET)
    public List<UserVo> get() {
        List<UserVo> userVos = new ArrayList<>();
        Iterable<User> users = userService.findAll();
        while (users.iterator().hasNext()) {
            userVos.add(convertFactory().convert(UserVo.class, users.iterator().next()));
        }
        return userVos;
    }

    /**
     * @param user_id
     * @return
     */
    @RequestMapping(value = "users/{user_id}", method = RequestMethod.GET)
    public UserVo get(@PathVariable String user_id) {
        User user = userService.findById(user_id).orElseThrow(() -> new IllegalArgumentException("用户不存在"));
        return convertFactory().convert(UserVo.class, user);
    }

    /**
     * @param user_id
     * @param userVo
     * @return
     */
    @RequestMapping(value = "user/{user_id}", method = RequestMethod.PUT)
    public User update(@PathVariable String user_id, @RequestBody UserVo userVo) {
        User user = userService.findById(user_id).orElseThrow(() -> new IllegalArgumentException("用户不存在"));
        BeanUtils.copyProperties(userVo, user, new String[]{"id"});
        return userService.save(user);
    }


}

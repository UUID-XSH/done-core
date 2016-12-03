package info.xsh.done.core.controller;

import info.xsh.done.core.controller.vo.UserVo;
import info.xsh.done.core.domain.User;
import info.xsh.done.core.service.ProjectService;
import info.xsh.done.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


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

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "users", method = RequestMethod.POST)
    public UserVo create(@RequestBody UserVo userVo) {
        User user = convertFactory().convert(User.class, userVo);
        user = userService.save(user);
        return convertFactory().convert(UserVo.class, user);
    }

    /**
     * 按照ID查询用户
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "users/{userId}", method = RequestMethod.GET)
    public UserVo getById(@PathVariable String userId) {
        User user = userService.findById(userId).orElseThrow(() -> new IllegalArgumentException("用户不存在"));
        return convertFactory().convert(UserVo.class, user);
    }

    /**
     * 按照用户名查询
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "users/name/{userName}", method = RequestMethod.GET)
    public UserVo getByName(@PathVariable String userName) {
        return convertFactory().convert(UserVo.class, userService.findByName(userName).orElseThrow(() -> new IllegalArgumentException("用户不存在")));
    }

    /**
     * 更新
     *
     * @param user_id
     * @param userVo
     * @return
     */
    @RequestMapping(value = "users/{user_id}", method = RequestMethod.PUT)
    public UserVo update(@PathVariable String user_id, @RequestBody UserVo userVo) {
        User user = userService.findById(user_id).orElseThrow(() -> new IllegalArgumentException("用户不存在"));
        BeanUtils.copyProperties(userVo, user, new String[]{"id"});
        return convertFactory().convert(UserVo.class, userService.save(user));
    }

    /**
     * 删除
     *
     * @param user_id
     * @return
     */
    @RequestMapping(value = "users/{user_id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable String user_id) {
        try {
            userService.delete(user_id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("删除用户失败！");
        }
        return "用户ID：" + user_id + "，删除成功！";
    }
}

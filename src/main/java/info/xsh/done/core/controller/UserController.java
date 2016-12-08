package info.xsh.done.core.controller;

import info.xsh.done.core.controller.vo.UserVo;
import info.xsh.done.core.domain.User;
import info.xsh.done.core.exception.DoneProjectException;
import info.xsh.done.core.exception.ExceptionCode;
import info.xsh.done.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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

    /**
     * @param userVo
     * @return
     */

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "users", method = RequestMethod.POST)
    public UserVo create(@RequestBody @Validated UserVo userVo) {
        User user = convert(User.class, userVo);
        user = userService.save(user);
        return convert(UserVo.class, user);
    }

    /**
     * 按照ID查询用户
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "users/{userId}", method = RequestMethod.GET)
    public UserVo getById(@PathVariable Long userId) {
        User user = userService.findById(userId).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "用户不存在!"));
        return convert(UserVo.class, user);
    }

    /**
     * 按照用户名查询
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "users/name/{userName}", method = RequestMethod.GET)
    public UserVo getByName(@PathVariable String userName) {
        return convert(UserVo.class, userService.findByName(userName).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "用户不存在!")));
    }

    /**
     * 更新
     *
     * @param userId
     * @param userVo
     * @return
     */
    @RequestMapping(value = "users/{userId}", method = RequestMethod.PUT)
    public UserVo update(@PathVariable Long userId, @RequestBody UserVo userVo) {
        User user = userService.findById(userId).orElseThrow(() -> new DoneProjectException(ExceptionCode.NOT_FOUND, "用户不存在!"));
        BeanUtils.copyProperties(userVo, user, new String[]{"id", "registerDate", "recentLoginTime"});
        return convert(UserVo.class, userService.save(user));
    }

    /**
     * 删除
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "users/{userId}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable Long userId) {
        try {
            userService.delete(userId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            new DoneProjectException(ExceptionCode.UN_KNOW, "删除用户失败!");
        }
        return true;
    }

}

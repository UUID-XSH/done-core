package info.xsh.done.core.controller;

import info.xsh.done.core.common.coverter.UserDoVoConverter;
import info.xsh.done.core.controller.vo.UserVo;
import info.xsh.done.core.domain.User;
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
public class UserController {

    @Autowired
    private UserService userService;

    private UserDoVoConverter userDoVoConverter = new UserDoVoConverter();

    /**
     * @param userVo
     * @return
     */
    @RequestMapping(value = "users", method = RequestMethod.POST)
    public UserVo create(@RequestBody UserVo userVo) {
        return userDoVoConverter.reverse().convert(userService.save(userDoVoConverter.convert(userVo)));
    }

    /**
     * @return
     */
    @RequestMapping(value = "users", method = RequestMethod.GET)
    public List<UserVo> get() {
        List<UserVo> userVos = new ArrayList<>();
        Iterable<User> users = userService.findAll();
        for (User user : users) {
            userVos.add(userDoVoConverter.reverse().convert(user));
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
        return userDoVoConverter.reverse().convert(user);
    }

    /**
     * 更新
     *
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

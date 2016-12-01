package info.xsh.done.core.controller;

<<<<<<< HEAD
import info.xsh.done.core.common.coverter.UserDoVoConverter;
=======
import info.xsh.done.core.controller.vo.ResponseVo;
>>>>>>> 39da7a00c6265d4450648cf44dd9d2ee2bc1497c
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
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

<<<<<<< HEAD
    private UserDoVoConverter userDoVoConverter = new UserDoVoConverter();
=======
>>>>>>> 53a1ef4527f4498dd9c39b2dddcb7b22cb0784b4

    /**
     * @param userVo
     * @return
     */
<<<<<<< HEAD
    @RequestMapping(value = "users", method = RequestMethod.POST)
    public UserVo create(@RequestBody UserVo userVo) {
        return userDoVoConverter.reverse().convert(userService.save(userDoVoConverter.convert(userVo)));
=======
    @RequestMapping(value = "users/create", method = RequestMethod.POST)
<<<<<<< HEAD
    public User create(@RequestBody UserVo userVo) {
        User user = convertFactory().convert(User.class, userVo);
        return userService.save(user);
>>>>>>> 53a1ef4527f4498dd9c39b2dddcb7b22cb0784b4
=======
    public ResponseVo create(@RequestBody UserVo userVo) {
        ResponseVo responseVo =new ResponseVo();
        User user = convertFactory.convert(User.class, userVo);
        responseVo.setCode(200);
        responseVo.setRes(userService.save(user));
        return responseVo;
>>>>>>> 39da7a00c6265d4450648cf44dd9d2ee2bc1497c
    }

    /**
     * @return
     */
<<<<<<< HEAD
    @RequestMapping(value = "users", method = RequestMethod.GET)
    public List<UserVo> get() {
=======
    @RequestMapping(value = "users/all", method = RequestMethod.GET)
    public ResponseVo get() {
        ResponseVo responseVo =new ResponseVo();
>>>>>>> 39da7a00c6265d4450648cf44dd9d2ee2bc1497c
        List<UserVo> userVos = new ArrayList<>();
        Iterable<User> users = userService.findAll();
<<<<<<< HEAD
        for (User user : users) {
            userVos.add(userDoVoConverter.reverse().convert(user));
=======
        while (users.iterator().hasNext()) {
            userVos.add(convertFactory().convert(UserVo.class, users.iterator().next()));
>>>>>>> 53a1ef4527f4498dd9c39b2dddcb7b22cb0784b4
        }
<<<<<<< HEAD

        return userVos;
=======
        responseVo.setCode(200);
        responseVo.setRes(userVos);
        return responseVo;
>>>>>>> 39da7a00c6265d4450648cf44dd9d2ee2bc1497c
    }

    /**
     * @param user_id
     * @return
     */
    @RequestMapping(value = "users/{user_id}", method = RequestMethod.GET)
    public ResponseVo get(@PathVariable String user_id) {
        ResponseVo responseVo =new ResponseVo();
        User user = userService.findById(user_id).orElseThrow(() -> new IllegalArgumentException("用户不存在"));
<<<<<<< HEAD
<<<<<<< HEAD
        return userDoVoConverter.reverse().convert(user);
=======
        responseVo.setCode(200);
        responseVo.setRes(convertFactory.convert(UserVo.class, user));
        return responseVo;
>>>>>>> 39da7a00c6265d4450648cf44dd9d2ee2bc1497c
    }

    /**
     * 更新
     *
=======
        return convertFactory().convert(UserVo.class, user);
    }

    /**
>>>>>>> 53a1ef4527f4498dd9c39b2dddcb7b22cb0784b4
     * @param user_id
     * @param userVo
     * @return
     */
    @RequestMapping(value = "user/{user_id}", method = RequestMethod.PUT)
    public ResponseVo update(@PathVariable String user_id, @RequestBody UserVo userVo) {
        ResponseVo responseVo =new ResponseVo();
        User user = userService.findById(user_id).orElseThrow(() -> new IllegalArgumentException("用户不存在"));
        BeanUtils.copyProperties(userVo, user, new String[]{"id"});
        responseVo.setCode(200);
        responseVo.setRes(userService.save(user));
        return responseVo;
    }


}

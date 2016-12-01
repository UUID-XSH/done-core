package info.xsh.done.core.controller;

import info.xsh.done.core.common.coverter.UserDoVoConverter;
import info.xsh.done.core.controller.vo.ResponseVo;
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

    private UserDoVoConverter userDoVoConverter = new UserDoVoConverter();

    /**
     * @param userVo
     * @return
     */
    @RequestMapping(value = "users", method = RequestMethod.POST)
    public ResponseVo create(@RequestBody UserVo userVo) {
        ResponseVo responseVo =new ResponseVo();
        User user = convertFactory().convert(User.class, userVo);
        responseVo.setCode(200);
        responseVo.setRes(userService.save(user));
        return responseVo;
    }

    /**
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

    /**
     * @param user_id
     * @return
     */
    @RequestMapping(value = "users/{user_id}", method = RequestMethod.GET)
    public ResponseVo get(@PathVariable String user_id) {
        ResponseVo responseVo =new ResponseVo();
        User user = userService.findById(user_id).orElseThrow(() -> new IllegalArgumentException("用户不存在"));
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
    @RequestMapping(value = "user/{user_id}", method = RequestMethod.PUT)
    public ResponseVo update(@PathVariable String user_id, @RequestBody UserVo userVo) {
        ResponseVo responseVo =new ResponseVo();
        User user = userService.findById(user_id).orElseThrow(() -> new IllegalArgumentException("用户不存在"));
        BeanUtils.copyProperties(userVo, user, new String[]{"id"});
        responseVo.setCode(200);
        responseVo.setRes(convertFactory().convert(UserVo.class,userService.save(user)));
        return responseVo;
    }


}

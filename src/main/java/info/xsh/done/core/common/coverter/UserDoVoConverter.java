package info.xsh.done.core.common.coverter;

import info.xsh.done.core.controller.vo.UserVo;
import info.xsh.done.core.domain.User;
import info.yannxia.java.chameleon.annonation.Convertor;
import org.springframework.stereotype.Component;

/**
 * Created by yangxueying on 2016/12/1.
 */
@Component
public class UserDoVoConverter {

    @Convertor
    public User doForward(UserVo userVo) {
        User user = new User();
        user.setPassWord(userVo.getPassWord());
        user.setName(userVo.getName());
        user.setEmail(userVo.getEmail());
        user.setNickName(userVo.getNickName());
        return user;
    }

    @Convertor
    public UserVo doBackward(User user) {
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setName(user.getName());
        userVo.setPassWord(user.getPassWord());
        userVo.setEmail(user.getEmail());
        userVo.setNickName(user.getNickName());
        userVo.setRecentLoginTime(user.getRecentLoginTime());
        userVo.setRegisterDate(user.getRegisterDate());
        return userVo;
    }


}

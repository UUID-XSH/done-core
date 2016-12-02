package info.xsh.done.core.common.coverter;

import com.google.common.base.Converter;
import info.xsh.done.core.controller.vo.UserVo;
import info.xsh.done.core.domain.User;
import info.yannxia.java.chameleon.annonation.Convertor;
import org.springframework.stereotype.Component;

/**
 * Created by yangxueying on 2016/12/1.
 */
@Component
public class UserDoVoConverter extends Converter<UserVo, User> {
    @Override
    @Convertor
    public User doForward(UserVo userVo) {
        User user = new User();
        user.setPassWord(userVo.getPassWord());
        user.setName(userVo.getName());
        user.setId(userVo.getId());
        user.setEmail(userVo.getEmail());
        user.setNickName(userVo.getNickName());
        user.setRegisterDate(userVo.getRegisterDate());
        user.setRecentLoginTime(userVo.getRecentLoginTime());
        return user;
    }

    @Override
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

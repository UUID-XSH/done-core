package info.xsh.done.core.config;

import info.xsh.done.core.domain.User;
import info.xsh.done.core.repository.UserRepository;
import info.xsh.done.core.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yangxueying on 2016/12/7.
 */
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userInfoRepository;

    public MyAuthenticationProvider(UserRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;

    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        String username = String.valueOf(auth.getPrincipal());
        String password = String.valueOf(auth.getCredentials());

        User user = userInfoRepository.findByName(username);

        if (!user.getPassWord().equalsIgnoreCase(EncryptUtils.encodeMD5String(password))) {
            throw new BadCredentialsException("Bad Credentials");
        }

        user.setPassWord("");

        return new UsernamePasswordAuthenticationToken(user.getName(), null, getAuthorities(user));
    }

    private Set<GrantedAuthority> getAuthorities(User user) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        return authorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}


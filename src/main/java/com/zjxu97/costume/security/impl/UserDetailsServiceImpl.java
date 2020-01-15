package com.zjxu97.costume.security.impl;

import com.zjxu97.costume.model.User;
import com.zjxu97.costume.security.LocalUserDetails;
import com.zjxu97.costume.service.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author zjxu97
 * @date 2020/1/16 00:42
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.getUserForSecurity(userName);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("userName{" + userName + "}");
        }
        return new LocalUserDetails(user);
    }
}

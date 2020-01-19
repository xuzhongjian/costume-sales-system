package com.zjxu97.costume.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.mapper.UserMapper;
import com.zjxu97.costume.model.User;
import com.zjxu97.costume.service.user.UserService;
import org.springframework.stereotype.Service;

/**
 * @author zjxu97
 * @date 2020/1/16 00:33
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User getUserForSecurity(String userName) {
        return this.getOne(qw().eq("user_name", userName));
    }

    private QueryWrapper<User> qw() {
        return new QueryWrapper<>();
    }
}

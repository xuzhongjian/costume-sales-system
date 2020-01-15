package com.zjxu97.costume.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.User;

/**
 * @author zjxu97
 * @date 2020/1/16 00:32
 */
public interface UserService extends IService<User> {
    User getUserForSecurity(String userName);
}

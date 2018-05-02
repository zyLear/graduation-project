package com.zylear.internalcontrol.admin.service.impl;

import com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol.UserMapper;
import com.zylear.internalcontrol.admin.domain.User;
import com.zylear.internalcontrol.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2018/4/29.
 */
@Component
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Override
    public User findByAccount(String account) {
        return userMapper.findByAccount(account);
    }

    @Override
    public User findByAccountAndPassword(String account, String password) {
        return userMapper.findByAccountAndPassword(account, password);
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }


    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

}

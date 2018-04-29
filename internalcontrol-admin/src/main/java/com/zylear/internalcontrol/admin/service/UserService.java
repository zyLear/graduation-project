package com.zylear.internalcontrol.admin.service;

import com.zylear.internalcontrol.admin.domain.User;

/**
 * Created by xiezongyu on 2018/4/29.
 */
public interface UserService {

    void insert(User user);

    User findByAccount(String account);

    User findByAccountAndPassword(String account, String password);

}

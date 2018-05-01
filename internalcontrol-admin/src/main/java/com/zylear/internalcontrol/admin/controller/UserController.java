package com.zylear.internalcontrol.admin.controller;

import com.zylear.internalcontrol.admin.bean.BasePageResult;
import com.zylear.internalcontrol.admin.domain.User;
import com.zylear.internalcontrol.admin.enums.Authority;
import com.zylear.internalcontrol.admin.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by xiezongyu on 2018/4/28.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @RequestMapping(value = "/login")
    public ModelAndView login() {
        return new ModelAndView("/user/login");
    }

    @RequestMapping(value = "/register-admin")
    public ModelAndView registerSdmin() {
        return new ModelAndView("/user/register-admin");
    }

    @ResponseBody
    @RequestMapping(value = "/sure-login")
    public BasePageResult sureLogin(HttpServletRequest request,
                                    @Param("account") String account,
                                    @Param("password") String password,
                                    @Param("authority") Integer authority) {

        User user = userService.findByAccountAndPassword(account, password);
        if (user == null) {
            return BasePageResult.ERROR_RESPONSE;
        }

        if (!user.getAuthority().equals(authority)) {
            return BasePageResult.ERROR_RESPONSE;
        }
        request.getSession().setAttribute("authority", authority);
        request.getSession().setAttribute("account", account);
        return BasePageResult.SUCCESS_RESPONSE;
    }

    @ResponseBody
    @RequestMapping(value = "/sure-register")
    public BasePageResult sureRegister(@Param("account") String account,
                                       @Param("password") String password,
                                       @Param("authority") Integer authority) {
        User user = userService.findByAccount(account);
        if (user != null) {
            return BasePageResult.ID_EXIST_RESPONSE;
        }
        user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setAuthority(authority);
        user.setIsDeleted(false);
        user.setCreateTime(new Date());
        user.setLastUpdateTime(new Date());
        userService.insert(user);
        return BasePageResult.SUCCESS_RESPONSE;
    }

    @ResponseBody
    @RequestMapping(value = "/sure-logout")
    public BasePageResult sureLogout(HttpServletRequest request) {
        request.getSession().removeAttribute("authority");
        request.getSession().removeAttribute("account");
        return BasePageResult.SUCCESS_RESPONSE;
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

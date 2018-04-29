package com.zylear.internalcontrol.admin.controller;

import com.zylear.internalcontrol.admin.bean.BasePageResult;
import com.zylear.internalcontrol.admin.enums.Authority;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiezongyu on 2018/4/28.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {


    @RequestMapping(value = "/login")
    public ModelAndView login() {
        return new ModelAndView("/user/login");
    }

    @ResponseBody
    @RequestMapping(value = "/sure-login")
    public BasePageResult sureLogin(HttpServletRequest request,
                                    @Param("account") String account,
                                    @Param("password") String password,
                                    @Param("authority") Integer authority) {
        if ("1".equals(account) && "1".equals(password)) {
            request.getSession().setAttribute("authority", authority);
            return BasePageResult.SUCCESS_RESPONSE;
        } else {
            return BasePageResult.ERROR_RESPONSE;
        }
    }


}

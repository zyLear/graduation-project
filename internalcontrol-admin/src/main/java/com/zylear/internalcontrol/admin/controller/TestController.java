package com.zylear.internalcontrol.admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xiezongyu on 2018/4/6.
 */
@Controller
@RequestMapping(value = "/test1")
public class TestController {

    @RequestMapping(value = "/test2")
    public ModelAndView test(){
        return new ModelAndView("test");
    }

}

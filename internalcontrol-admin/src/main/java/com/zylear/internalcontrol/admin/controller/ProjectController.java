package com.zylear.internalcontrol.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xiezongyu on 2018/4/6.
 */
@Controller
@RequestMapping(value = "/project")
public class ProjectController {

    @RequestMapping("/application")
    public ModelAndView application() {
        return new ModelAndView("project/application");
    }

    @RequestMapping("/test")
    public ModelAndView test() {
        return new ModelAndView("project/application");
    }

}

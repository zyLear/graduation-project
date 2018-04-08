package com.zylear.internalcontrol.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by xiezongyu on 2018/4/6.
 */
@Controller
@RequestMapping(value = "/project")
public class ProjectController {

    @RequestMapping("/project-application")
    public ModelAndView application() {
        return new ModelAndView("project/project-application");
    }

    @RequestMapping("/test")
    public ModelAndView test(HttpServletResponse response) {

        return new ModelAndView("project/project-application");
    }

}

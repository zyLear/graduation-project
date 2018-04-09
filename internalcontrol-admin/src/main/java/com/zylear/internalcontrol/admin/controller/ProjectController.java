package com.zylear.internalcontrol.admin.controller;

import com.zylear.internalcontrol.admin.bean.PageResult;
import com.zylear.internalcontrol.admin.bean.TestViewBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by xiezongyu on 2018/4/6.
 */
@Controller
@RequestMapping(value = "/project")
public class ProjectController {

    @RequestMapping("/project-list")
    public ModelAndView projectList() {
        return new ModelAndView("project/project-list");
    }

    @RequestMapping("/project-application")
    public ModelAndView application() {
        return new ModelAndView("project/project-application");
    }

    @ResponseBody
    @RequestMapping("/sure-project-application")
    public String sureProjectApplication(@RequestParam("filePath") MultipartFile file,
                                         @RequestParam("projectName") String projectName) {
        System.out.println(projectName);
        try {
            file.transferTo(new File("C:\\Users\\表哥小珠\\Desktop\\files\\"+file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "yes";
    }

//    @RequestMapping("/test")
//    public ModelAndView test(HttpServletResponse response) {
//
//        return new ModelAndView("project/project-application");
//    }

}

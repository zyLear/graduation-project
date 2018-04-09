package com.zylear.internalcontrol.admin.controller;

import com.zylear.internalcontrol.admin.bean.BasePageResult;
import com.zylear.internalcontrol.admin.bean.PageResult;
import com.zylear.internalcontrol.admin.bean.TestViewBean;
import com.zylear.internalcontrol.admin.manager.ProjectManager;
import com.zylear.internalcontrol.admin.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    private String filePathPrefix;
    private ProjectService projectService;
    private ProjectManager projectManager;

    private static final String PROJECT_FILE_DIRECTORY = "project/";

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
    public BasePageResult sureProjectApplication(@RequestParam("projectNumber") String projectNumber,
                                                 @RequestParam("projectName") String projectName,
                                                 @RequestParam("applicant") String applicant,
                                                 @RequestParam("applicationDepartment") String applicationDepartment,
                                                 @RequestParam("projectContent") String projectContent,
                                                 @RequestParam("projectBudget") Double projectBudget,
                                                 @RequestParam("file") MultipartFile file
    ) {

        return projectManager.saveProjectApplication(projectNumber, projectName, applicant,
                applicationDepartment, projectContent, projectBudget, file);
    }

//    @RequestMapping("/test")
//    public ModelAndView test(HttpServletResponse response) {
//
//        return new ModelAndView("project/project-application");
//    }


    @Autowired
    public void setFilePathPrefix(String filePathPrefix) {
        this.filePathPrefix = filePathPrefix;
    }

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Autowired
    public void setProjectManager(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }
}

package com.zylear.internalcontrol.admin.controller;

import com.zylear.internalcontrol.admin.bean.BasePageResult;
import com.zylear.internalcontrol.admin.bean.PageResult;
import com.zylear.internalcontrol.admin.bean.TestViewBean;
import com.zylear.internalcontrol.admin.domain.ProjectBudget;
import com.zylear.internalcontrol.admin.manager.BudgetManager;
import com.zylear.internalcontrol.admin.manager.ProjectManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xiezongyu on 2018/4/8.
 */
@Controller
@RequestMapping(value = "/budget")
public class BudgetController {


    private BudgetManager budgetManager;
    private ProjectManager projectManager;

    @RequestMapping("/budget-list")
    public ModelAndView budgetList() {
        return new ModelAndView("budget/budget-list");
    }

    @RequestMapping("/budget-application")
    public ModelAndView application() {
        return new ModelAndView("budget/budget-application");
    }


    @RequestMapping("/budget-submit")
    public ModelAndView budgetSubmit() {
        return new ModelAndView("budget/budget-submit");
    }

    @ResponseBody
    @RequestMapping("/sure-budget-application")
    public BasePageResult sureBudgetApplication(@RequestParam("projectNumber") String projectNumber,
                                                @RequestParam("items") String items
    ) {
        return budgetManager.saveBudget(projectNumber, items);
    }


    @RequestMapping("/show-budget")
    public ModelAndView showBudget(@RequestParam("projectNumber") String projectNumber) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("budget/show-budget");
        modelAndView.addObject("project",projectManager.findProjectViewBean(projectNumber));
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping("/budget-detail")
    public PageResult budgetDetail() {
        PageResult pageResult = new PageResult();
        pageResult.setTotal(4);
        pageResult.setRows(Arrays.asList(new TestViewBean("1"), new TestViewBean("1"), new TestViewBean("1"), new TestViewBean("1")));
        return pageResult;
    }

    @Autowired
    public void setBudgetManager(BudgetManager budgetManager) {
        this.budgetManager = budgetManager;
    }

    @Autowired
    public void setProjectManager(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }
}

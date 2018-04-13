package com.zylear.internalcontrol.admin.controller;

import com.zylear.internalcontrol.admin.bean.BasePageResult;
import com.zylear.internalcontrol.admin.bean.PageResult;
import com.zylear.internalcontrol.admin.bean.TestViewBean;
import com.zylear.internalcontrol.admin.manager.BudgetManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Created by xiezongyu on 2018/4/8.
 */
@Controller
@RequestMapping(value = "/budget")
public class BudgetController {


    private BudgetManager budgetManager;

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
                                                @RequestParam("budgetAspect") String budgetAspect,
                                                @RequestParam("budgetContent") String budgetContent,
                                                @RequestParam("budgetMoney") Double budgetMoney
    ) {

        return budgetManager.saveBudget(projectNumber, budgetAspect, budgetContent, budgetMoney);
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
}

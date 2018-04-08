package com.zylear.internalcontrol.admin.controller;

import com.zylear.internalcontrol.admin.bean.PageResult;
import com.zylear.internalcontrol.admin.bean.TestViewBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Created by xiezongyu on 2018/4/8.
 */
@Controller
@RequestMapping(value = "/budget")
public class BudgetController {

    @RequestMapping("/budget-application")
    public ModelAndView application() {
        return new ModelAndView("budget/budget-application");
    }


    @ResponseBody
    @RequestMapping("/budget-detail")
    public PageResult budgetDetail() {
        PageResult pageResult = new PageResult();
        pageResult.setTotal(4);
        pageResult.setRows(Arrays.asList(new TestViewBean("1"), new TestViewBean("1"), new TestViewBean("1"), new TestViewBean("1")));
        return pageResult;
    }
}

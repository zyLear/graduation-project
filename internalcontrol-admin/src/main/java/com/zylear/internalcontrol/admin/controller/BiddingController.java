package com.zylear.internalcontrol.admin.controller;

import com.zylear.internalcontrol.admin.bean.PageResult;
import com.zylear.internalcontrol.admin.bean.TestViewBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

/**
 * Created by xiezongyu on 2018/4/8.
 */
@Controller
@RequestMapping(value = "/bidding")
public class BiddingController {


    @RequestMapping("/bidding-list-page")
    public ModelAndView biddingListPage() {
        return new ModelAndView("bidding/bidding-list-page");
    }

    @RequestMapping("/bidding-create-page")
    public ModelAndView biddingCreatePage() {
        return new ModelAndView("bidding/bidding-create-page");
    }


    @ResponseBody
    @RequestMapping("/bidding-detail")
    public PageResult budgetDetail() {
        PageResult pageResult = new PageResult();
        pageResult.setTotal(4);
        pageResult.setRows(Arrays.asList(new TestViewBean("1"), new TestViewBean("1"), new TestViewBean("1"), new TestViewBean("1")));
        return pageResult;
    }
}

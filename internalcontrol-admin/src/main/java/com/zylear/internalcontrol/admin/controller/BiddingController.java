package com.zylear.internalcontrol.admin.controller;

import com.zylear.internalcontrol.admin.bean.BasePageResult;
import com.zylear.internalcontrol.admin.bean.PageResult;
import com.zylear.internalcontrol.admin.bean.TestViewBean;
import com.zylear.internalcontrol.admin.manager.BiddingManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

/**
 * Created by xiezongyu on 2018/4/8.
 */
@Controller
@RequestMapping(value = "/bidding")
public class BiddingController {

    private static final Logger logger = LoggerFactory.getLogger(BiddingController.class);
    private BiddingManager biddingManager;


    @RequestMapping("/bidding-list")
    public ModelAndView biddingListPage() {
        return new ModelAndView("bidding/bidding-list-page");
    }

    @RequestMapping("/bidding-create")
    public ModelAndView biddingCreatePage() {
        return new ModelAndView("bidding/bidding-create");
    }

    @ResponseBody
    @RequestMapping("/sure-bidding-create")
    public BasePageResult sureProjectApplication(@RequestParam("projectNumber") String projectNumber,
                                                 @RequestParam("biddingNumber") String biddingNumber,
                                                 @RequestParam("biddingName") String biddingName,
                                                 @RequestParam("biddingContent") String biddingContent,
                                                 @RequestParam("prices") Double prices,
                                                 @RequestParam("file") MultipartFile file
    ) {

        return biddingManager.saveBidding(projectNumber, biddingNumber, biddingName, biddingContent, prices, file);
    }


    @ResponseBody
    @RequestMapping("/bidding-detail")
    public PageResult budgetDetail() {
        PageResult pageResult = new PageResult();
        pageResult.setTotal(4);
        pageResult.setRows(Arrays.asList(new TestViewBean("1"), new TestViewBean("1"), new TestViewBean("1"), new TestViewBean("1")));
        return pageResult;
    }


    @Autowired
    public void setBiddingManager(BiddingManager biddingManager) {
        this.biddingManager = biddingManager;
    }
}

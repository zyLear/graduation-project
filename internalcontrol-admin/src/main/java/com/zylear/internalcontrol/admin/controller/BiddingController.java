package com.zylear.internalcontrol.admin.controller;

import com.zylear.internalcontrol.admin.bean.*;
import com.zylear.internalcontrol.admin.manager.BiddingManager;
import org.apache.ibatis.annotations.Param;
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
import java.util.Date;

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
        return new ModelAndView("bidding/bidding-list");
    }

    @RequestMapping("/bidding-create")
    public ModelAndView biddingCreatePage() {
        return new ModelAndView("bidding/bidding-create");
    }

    @ResponseBody
    @RequestMapping("/get-bidding-list")
    public PageResult<BiddingViewBean> getBiddingList(@Param("limit") Integer limit,
                                                      @Param("offset") Integer offset) {
        if (offset == null) {
            offset = 0;
        }
        if (limit == null || limit > 100) {
            limit = 10;
        }
        PageParam pageParam = new PageParam(limit, offset);
        return biddingManager.getBiddingListPageResult(pageParam);
    }

    @ResponseBody
    @RequestMapping("/sure-bidding-create")
    public BasePageResult saveBidding(@RequestParam("projectNumber") String projectNumber,
                                      @RequestParam("biddingNumber") String biddingNumber,
                                      @RequestParam("biddingName") String biddingName,
                                      @RequestParam("biddingContent") String biddingContent,
                                      @RequestParam("biddingStartTime") Long biddingStartTime,
                                      @RequestParam("biddingEndTime") Long biddingEndTime,
                                      @RequestParam("prices") Double prices,
                                      @RequestParam("file") MultipartFile file) {
        return biddingManager.saveBidding(projectNumber, biddingNumber, biddingName,
                new Date(biddingStartTime), new Date(biddingEndTime), biddingContent, prices, file);
    }


    @ResponseBody
    @RequestMapping("/change-bidding-status")
    public BasePageResult changeBiddingStatus(@Param("biddingNumber") String biddingNumber,
                                              @Param("biddingStatus") Integer biddingStatus) {
        return biddingManager.changeBiddingStatus(biddingNumber, biddingStatus);
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

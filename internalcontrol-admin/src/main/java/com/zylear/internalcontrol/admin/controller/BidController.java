package com.zylear.internalcontrol.admin.controller;

import com.zylear.internalcontrol.admin.bean.*;
import com.zylear.internalcontrol.admin.domain.ProjectBid;
import com.zylear.internalcontrol.admin.manager.BidManager;
import com.zylear.internalcontrol.admin.manager.BiddingManager;
import com.zylear.internalcontrol.admin.service.ProjectBidService;
import com.zylear.internalcontrol.admin.service.ProjectBiddingService;
import com.zylear.internalcontrol.admin.service.ProjectService;
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

/**
 * Created by xiezongyu on 2018/4/15.
 */
@Controller
@RequestMapping(value = "/bid")
public class BidController {

    private static final Logger logger = LoggerFactory.getLogger(BidController.class);
    private BiddingManager biddingManager;
    private BidManager bidManager;

    @RequestMapping("/bidding-list")
    public ModelAndView biddingListPage() {
        return new ModelAndView("bid/bidding-list");
    }

    @RequestMapping("/bid-list")
    public ModelAndView bidListPage() {
        return new ModelAndView("bid/bid-list");
    }

    @RequestMapping("/custom-bid-list")
    public ModelAndView customBidListPage(@Param("biddingNumber") String biddingNumber) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bid/custom-bid-list");
        modelAndView.addObject("biddingNumber", biddingNumber);
        return modelAndView;
    }


    @RequestMapping("/bid-create")
    public ModelAndView bidCreate(@Param("biddingNumber") String biddingNumber) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bid/bid-create");
        modelAndView.addObject("bidding", biddingManager.getBiddingViewBean(biddingNumber));
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/sure-bid-create")
    public BasePageResult sureBidCreate(@RequestParam("biddingNumber") String biddingNumber,
                                        @RequestParam("bidCompany") String bidCompany,
                                        @RequestParam("bidContent") String bidContent,
                                        @RequestParam("bidPrices") Double bidPrices,
                                        @RequestParam("file") MultipartFile file) {
        System.out.println("jing");
        return bidManager.saveBid(biddingNumber, bidCompany, bidContent, bidPrices, file);
    }


    @ResponseBody
    @RequestMapping("/sure-choose-bid")
    public BasePageResult sureChooseBid(@RequestParam("bidNumber") String bidNumber) {
        return bidManager.sureChooseBid(bidNumber);
    }

//    public BasePageResult sureProjectApplication(@RequestParam("projectNumber") String projectNumber,
//                                                 @RequestParam("biddingNumber") String biddingNumber,
//                                                 @RequestParam("biddingName") String biddingName,
//                                                 @RequestParam("biddingContent") String biddingContent,
//                                                 @RequestParam("prices") Double prices,
//                                                 @RequestParam("file") MultipartFile file) {
//        return biddingManager.saveBidding(projectNumber, biddingNumber, biddingName, biddingContent, prices, file);
//    }

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
    @RequestMapping("/get-bid-list")
    public PageResult<BidViewBean> getBidList(@Param("limit") Integer limit,
                                              @Param("offset") Integer offset) {
        if (offset == null) {
            offset = 0;
        }
        if (limit == null || limit > 100) {
            limit = 10;
        }
        System.out.println();
        PageParam pageParam = new PageParam(limit, offset);
        return bidManager.getBidListPageResult(pageParam);
    }

    @ResponseBody
    @RequestMapping("/get-bids")
    public BasePageResult<ProjectBid> getProjectBidList(@RequestParam("bidStatus") Integer bidStatus) {
        return bidManager.queryProjectBids(bidStatus);
    }


    @ResponseBody
    @RequestMapping("/get-custom-bid-list")
    public PageResult<BidViewBean> getCustomBidList(@Param("biddingNumber") String biddingNumber,
                                                    @Param("limit") Integer limit,
                                                    @Param("offset") Integer offset) {
        if (offset == null) {
            offset = 0;
        }
        if (limit == null || limit > 100) {
            limit = 10;
        }
        PageParam pageParam = new PageParam(limit, offset);
        return bidManager.getCustomBidListPageResult(biddingNumber, pageParam);
    }


    @Autowired
    public void setBiddingManager(BiddingManager biddingManager) {
        this.biddingManager = biddingManager;
    }

    @Autowired
    public void setBidManager(BidManager bidManager) {
        this.bidManager = bidManager;
    }
}

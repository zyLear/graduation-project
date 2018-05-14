package com.zylear.internalcontrol.admin.controller;

import com.zylear.internalcontrol.admin.bean.*;
import com.zylear.internalcontrol.admin.domain.ProjectBid;
import com.zylear.internalcontrol.admin.domain.ProjectContract;
import com.zylear.internalcontrol.admin.manager.BidManager;
import com.zylear.internalcontrol.admin.manager.BiddingManager;
import com.zylear.internalcontrol.admin.manager.ContractManager;
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
 * Created by xiezongyu on 2018/4/11.
 */
@Controller
@RequestMapping(value = "/contract")
public class ContractController {

    private static final Logger logger = LoggerFactory.getLogger(ContractController.class);
    private ContractManager contractManager;
    private BidManager bidManager;


    @RequestMapping("/contract-list")
    public ModelAndView contractListPage() {
        return new ModelAndView("contract/contract-list");
    }

    @RequestMapping("/show-contract")
    public ModelAndView showContract(@Param("bidNumber") String bidNumber) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("contract/show-contract");
        modelAndView.addObject("contract", contractManager.findContractViewBeanByBidNumber(bidNumber));
        return modelAndView;
    }

    @RequestMapping("/contract-create")
    public ModelAndView contractCreatePage() {
        return new ModelAndView("contract/contract-create");
    }

    @RequestMapping("/specified-contract-create")
    public ModelAndView specifiedContractCreatePage(@Param("bidNumber") String bidNumber) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("contract/specified-contract-create");
        modelAndView.addObject("bid", bidManager.findBidViewBean(bidNumber));
        return modelAndView;
    }

    @RequestMapping("/edit-items")
    public ModelAndView editItems(@RequestParam("contractNumber") String contractNumber) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("contract/edit-items");
        modelAndView.addObject("contract", contractManager.findContractViewBean(contractNumber));
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/get-contract-list")
    public PageResult<ContractViewBean> getBiddingList(@Param("limit") Integer limit,
                                                       @Param("offset") Integer offset) {
        if (offset == null) {
            offset = 0;
        }
        if (limit == null || limit > 100) {
            limit = 10;
        }
        PageParam pageParam = new PageParam(limit, offset);
        return contractManager.getContractListPageResult(pageParam);
    }

    @ResponseBody
    @RequestMapping("/sure-finish-item")
    public BasePageResult sureFinishItem(@Param("itemId") Integer itemId) {
        return contractManager.sureFinishItem(itemId);
    }


    @ResponseBody
    @RequestMapping("/sure-contract-create")
    public BasePageResult sureContractCreate(@RequestParam("bidNumber") String bidNumber,
                                             @RequestParam("contractNumber") String contractNumber,
                                             @RequestParam("contractName") String contractName,
                                             @RequestParam("contractContent") String contractContent,
                                             @RequestParam("file") MultipartFile file,
                                             @RequestParam("items") String items
    ) {

        return contractManager.saveContract(bidNumber, contractNumber, contractName, contractContent, file, items);
    }

    @ResponseBody
    @RequestMapping("/get-contracts")
    public BasePageResult<ProjectContract> getProjectBidList(@RequestParam("contractStatus") Integer contractStatus) {
        return contractManager.queryProjectContracts(contractStatus);
    }


    @ResponseBody
    @RequestMapping("/get-contract-content")
    public BasePageResult<ContractViewBean> getProjectContent(@RequestParam("contractNumber") String contractNumber) {
        return contractManager.getContractContent(contractNumber);
    }


    @Autowired
    public void setContractManager(ContractManager contractManager) {
        this.contractManager = contractManager;
    }

    @Autowired
    public void setBidManager(BidManager bidManager) {
        this.bidManager = bidManager;
    }
}

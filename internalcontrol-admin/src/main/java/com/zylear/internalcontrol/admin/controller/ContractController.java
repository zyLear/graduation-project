package com.zylear.internalcontrol.admin.controller;

import com.zylear.internalcontrol.admin.bean.BasePageResult;
import com.zylear.internalcontrol.admin.manager.BiddingManager;
import com.zylear.internalcontrol.admin.manager.ContractManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    @RequestMapping("/contract-list")
    public ModelAndView contractListPage() {
        return new ModelAndView("bidding/bidding-list-page");
    }

    @RequestMapping("/contract-create")
    public ModelAndView contractCreatePage() {
        return new ModelAndView("bidding/bidding-create");
    }

    @ResponseBody
    @RequestMapping("/sure-contract-create")
    public BasePageResult sureContractCreate(@RequestParam("bidNumber") String bidNumber,
                                                 @RequestParam("contractNumber") String contractNumber,
                                                 @RequestParam("contractName") String contractName,
                                                 @RequestParam("contractContent") String contractContent,
                                                 @RequestParam("contractMoney") Double contractMoney,
                                                 @RequestParam("file") MultipartFile file
    ) {

        return contractManager.saveContract(bidNumber, contractNumber, contractName, contractContent, contractMoney, file);
    }


}

package com.zylear.internalcontrol.admin.controller;

import com.zylear.internalcontrol.admin.bean.AssetViewBean;
import com.zylear.internalcontrol.admin.bean.BasePageResult;
import com.zylear.internalcontrol.admin.bean.PageParam;
import com.zylear.internalcontrol.admin.bean.PageResult;
import com.zylear.internalcontrol.admin.manager.AssetManager;
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
@RequestMapping(value = "/asset")
public class AssetController {

    private static final Logger logger = LoggerFactory.getLogger(AssetController.class);
    private AssetManager assetManager;


//    @RequestMapping("/contract-list")
//    public ModelAndView contractListPage() {
//        return new ModelAndView("bidding/bidding-list-page");
//    }
//
    @RequestMapping("/add-asset")
    public ModelAndView contractCreatePage() {
        return new ModelAndView("asset/add-asset");
    }

    @RequestMapping("/asset-list")
    public ModelAndView biddingListPage() {
        return new ModelAndView("asset/asset-list");
    }


    @ResponseBody
    @RequestMapping("/get-bidding-list")
    public PageResult<AssetViewBean> getBiddingList(@Param("limit") Integer limit,
                                                    @Param("offset") Integer offset) {
        if (offset == null) {
            offset = 0;
        }
        if (limit == null || limit > 100) {
            limit = 10;
        }
        PageParam pageParam = new PageParam(limit, offset);
        return assetManager.getAssetListPageResult(pageParam);
    }

    @ResponseBody
    @RequestMapping("/sure-add-asset")
    public BasePageResult sureContractCreate(@RequestParam("contractNumber") String contractNumber,
                                             @RequestParam("assetNumber") String assetNumber,
                                             @RequestParam("assetType") String assetType,
                                             @RequestParam("remark") String remark,
                                             @RequestParam("prices") Double prices/*,
                                             @RequestParam("count") Integer count*/
    ) {

        return assetManager.addAsset(contractNumber, assetNumber, assetType, remark, prices/*, count*/);
    }





    @Autowired
    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }
}

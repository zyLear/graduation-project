package com.zylear.internalcontrol.admin.manager;

import com.zylear.internalcontrol.admin.bean.BasePageResult;
import com.zylear.internalcontrol.admin.domain.Asset;
import com.zylear.internalcontrol.admin.domain.ProjectContract;
import com.zylear.internalcontrol.admin.service.AssetService;
import com.zylear.internalcontrol.admin.service.ProjectBudgetService;
import com.zylear.internalcontrol.admin.service.ProjectContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by xiezongyu on 2018/4/11.
 */
@Component
public class AssetManager {

    private static final Logger logger = LoggerFactory.getLogger(AssetManager.class);

    private AssetService assetService;
    private ProjectContractService projectContractService;

    public BasePageResult addAsset(String contractNumber, String assetNumber, String assetType, String remark, Double prices/*, Integer count*/) {

        ProjectContract projectContract = projectContractService.findByContractNumber(contractNumber);
        if (projectContract == null) {
            return BasePageResult.ERROR_RESPONSE;
        }
        //calculate the all money of the contarct
        // if (projectContract.getContractMoney()<count*ass)

        Asset asset = new Asset();
        asset.setContractNumber(contractNumber);
        asset.setAssetNumber(assetNumber);
        asset.setAssetType(assetType);
        asset.setRemark(remark);
        asset.setPrices(prices);
        asset.setIsDeleted(false);
        asset.setCreateTime(new Date());
        asset.setLastUpdateTime(new Date());

//        for (int i = 0; i < count; i++) {
            assetService.insert(asset);
//        }
        return BasePageResult.SUCCESS_RESPONSE;
    }


    @Autowired
    public void setAssetService(AssetService assetService) {
        this.assetService = assetService;
    }

    @Autowired
    public void setProjectContractService(ProjectContractService projectContractService) {
        this.projectContractService = projectContractService;
    }
}

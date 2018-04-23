package com.zylear.internalcontrol.admin.manager;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.zylear.internalcontrol.admin.bean.AssetViewBean;
import com.zylear.internalcontrol.admin.bean.BasePageResult;
import com.zylear.internalcontrol.admin.bean.PageParam;
import com.zylear.internalcontrol.admin.bean.PageResult;
import com.zylear.internalcontrol.admin.domain.Asset;
import com.zylear.internalcontrol.admin.domain.Project;
import com.zylear.internalcontrol.admin.domain.ProjectBidding;
import com.zylear.internalcontrol.admin.domain.ProjectContract;
import com.zylear.internalcontrol.admin.enums.ContractStatus;
import com.zylear.internalcontrol.admin.service.AssetService;
import com.zylear.internalcontrol.admin.service.ProjectBudgetService;
import com.zylear.internalcontrol.admin.service.ProjectContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            return BasePageResult.CONTRACT_NO_EXIST_RESPONSE;
        }

        if (!ContractStatus.effective.getValue().equals(projectContract.getContractStatus())) {
            return BasePageResult.ERROR_RESPONSE;
        }

        Asset asset = assetService.findByAssetNumber(assetNumber);
        if (asset != null) {
            return BasePageResult.ID_EXIST_RESPONSE;
        }
        //calculate the all money of the contarct
        // if (projectContract.getContractMoney()<count*ass)

        asset = new Asset();
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


    public PageResult<AssetViewBean> getAssetListPageResult(PageParam pageParam) {
        PageResult<AssetViewBean> pageResult = new PageResult<>();
        List<Asset> assets = assetService.findByPageParam(pageParam);
        pageResult.setTotal(assetService.getTotal());
        pageResult.setRows(toAssetViewBean(assets));
        return pageResult;
    }

    private List<AssetViewBean> toAssetViewBean(List<Asset> assets) {
        List<AssetViewBean> list = new ArrayList<>(assets.size());
        for (Asset asset : assets) {
            String contractNumber = asset.getContractNumber();
            AssetViewBean assetViewBean = new AssetViewBean();
            if ("none".equals(contractNumber)) {
                assetViewBean.setContractNumber("无");
                assetViewBean.setContractName("无");
            }
            ProjectContract projectContract = projectContractService.findByContractNumber(asset.getContractNumber());
            if (projectContract == null) {
                assetViewBean.setContractNumber("无");
                assetViewBean.setContractName("无");
            }
            assetViewBean.setContractName(projectContract.getContractName());
            assetViewBean.setContractNumber(projectContract.getContractNumber());
            assetViewBean.setAssetNumber(asset.getAssetNumber());
            assetViewBean.setAssetType(asset.getAssetType());
            assetViewBean.setPrices(asset.getPrices());
            assetViewBean.setRemark(asset.getRemark());
            assetViewBean.setCreateTime(asset.getCreateTime());
            list.add(assetViewBean);
        }
        return list;
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

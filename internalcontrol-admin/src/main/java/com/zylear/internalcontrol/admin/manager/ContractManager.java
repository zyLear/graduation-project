package com.zylear.internalcontrol.admin.manager;

import com.zylear.internalcontrol.admin.bean.BasePageResult;
import com.zylear.internalcontrol.admin.config.DataSourceInternalControlConfig;
import com.zylear.internalcontrol.admin.constant.FileDirectory;
import com.zylear.internalcontrol.admin.controller.BiddingController;
import com.zylear.internalcontrol.admin.domain.ProjectBid;
import com.zylear.internalcontrol.admin.domain.ProjectContract;
import com.zylear.internalcontrol.admin.domain.ProjectContractItem;
import com.zylear.internalcontrol.admin.enums.ContractStatus;
import com.zylear.internalcontrol.admin.service.ProjectContractItemService;
import com.zylear.internalcontrol.admin.service.ProjectContractService;
import com.zylear.internalcontrol.admin.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by xiezongyu on 2018/4/11.
 */
@Component
public class ContractManager {

    private static final Logger logger = LoggerFactory.getLogger(ContractManager.class);

    private String filePathPrefix;
    private ProjectContractService projectContractService;
    private ProjectContractItemService projectContractItemService;

    @Transactional(value = DataSourceInternalControlConfig.TX_MANAGER)
    public BasePageResult saveContract(String bidNumber, String contractNumber, String contractName, String contractContent, MultipartFile file, String items) {

        List<ProjectContractItem> projectContractItems;
        try {
            projectContractItems = JsonUtil.parseJsonToList(items, ProjectContractItem.class);
        } catch (Exception e) {
            logger.error("parse json to list fail. string:{}", items, e);
            return BasePageResult.ERROR_RESPONSE;
        }

        if (projectContractItems == null || projectContractItems.isEmpty()) {
            logger.info("items is empty");
            return BasePageResult.ERROR_RESPONSE;
        }

        ProjectContract projectContract = projectContractService.findByContractNumber(contractNumber);
//        System.out.println("test:"+projectContract.getCreateTime());
        if (projectContract != null) {
            return BasePageResult.ID_EXIST_RESPONSE;
        }
        projectContract = projectContractService.findByContractName(contractName);
        if (projectContract != null) {
            return BasePageResult.ID_EXIST_RESPONSE;
        }
        projectContract = projectContractService.findByFilePath(FileDirectory.CONTRACT_FILE_DIRECTORY + file.getOriginalFilename());
        if (projectContract != null) {
            return BasePageResult.FILE_EXIST_RESPONSE;
        }


        try {
            file.transferTo(new File(filePathPrefix + FileDirectory.CONTRACT_FILE_DIRECTORY + file.getOriginalFilename()));
            logger.info("save file path:{}", filePathPrefix + FileDirectory.CONTRACT_FILE_DIRECTORY + file.getOriginalFilename());
        } catch (IOException e) {
            logger.error("save file error. fileName:{}", file.getOriginalFilename(), e);
            return BasePageResult.UPLOAD_FAIL_RESPONSE;
        }

        Double money = 0.0;

        for (ProjectContractItem item : projectContractItems) {
            money += item.getItemMoney();
        }

        projectContract = new ProjectContract();
        projectContract.setBidNumber(bidNumber);
        projectContract.setContractNumber(contractNumber);
        projectContract.setContractName(contractName);
        projectContract.setContractContent(contractContent);
        projectContract.setContractMoney(money);
        projectContract.setContractStatus(ContractStatus.effective.getValue());
        projectContract.setFilePath(FileDirectory.CONTRACT_FILE_DIRECTORY + file.getOriginalFilename());
        projectContract.setIsDeleted(false);
        projectContract.setCreateTime(new Date());
        projectContract.setLastUpdateTime(new Date());
        projectContractService.insert(projectContract);


        for (ProjectContractItem item : projectContractItems) {
            item.setContractNumber(contractNumber);
            item.setIsDeleted(false);
            item.setCreateTime(new Date());
            item.setLastUpdateTime(new Date());
            projectContractItemService.insert(item);
        }

        return BasePageResult.SUCCESS_RESPONSE;

    }

    public BasePageResult<ProjectContract> queryProjectContracts(Integer contractStatus) {

        BasePageResult<ProjectContract> response = BasePageResult.getSuccessResponse();
        response.setData(projectContractService.findByStatus(contractStatus));
        return response;
    }





    @Autowired
    public void setProjectContractService(ProjectContractService projectContractService) {
        this.projectContractService = projectContractService;
    }

    @Autowired
    public void setProjectContractItemService(ProjectContractItemService projectContractItemService) {
        this.projectContractItemService = projectContractItemService;
    }

    @Autowired
    public void setFilePathPrefix(String filePathPrefix) {
        this.filePathPrefix = filePathPrefix;
    }


}

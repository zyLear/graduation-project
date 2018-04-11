package com.zylear.internalcontrol.admin.manager;

import com.zylear.internalcontrol.admin.bean.BasePageResult;
import com.zylear.internalcontrol.admin.constant.FileDirectory;
import com.zylear.internalcontrol.admin.controller.BiddingController;
import com.zylear.internalcontrol.admin.domain.ProjectContract;
import com.zylear.internalcontrol.admin.enums.ContractStatus;
import com.zylear.internalcontrol.admin.service.ProjectContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by xiezongyu on 2018/4/11.
 */
@Component
public class ContractManager {

    private static final Logger logger = LoggerFactory.getLogger(ContractManager.class);

    private String filePathPrefix;
    private ProjectContractService projectContractService;

    public BasePageResult saveContract(String bidNumber, String contractNumber, String contractName, String contractContent, Double contractMoney, MultipartFile file) {
        ProjectContract projectContract = projectContractService.findByContractNumber(contractNumber);
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

        projectContract = new ProjectContract();
        projectContract.setBidNumber(bidNumber);
        projectContract.setContractNumber(contractNumber);
        projectContract.setContractName(contractName);
        projectContract.setContractContent(contractContent);
        projectContract.setContractMoney(contractMoney);
        projectContract.setContractStatus(ContractStatus.editing.getValue());
        projectContract.setFilePath(FileDirectory.CONTRACT_FILE_DIRECTORY + file.getOriginalFilename());
        projectContract.setIsDeleted(false);
        projectContract.setCreateTime(new Date());
        projectContract.setLastUpdateTime(new Date());
        projectContractService.insert(projectContract);

        return BasePageResult.SUCCESS_RESPONSE;


    }

    @Autowired
    public void setProjectContractService(ProjectContractService projectContractService) {
        this.projectContractService = projectContractService;
    }

    @Autowired
    public void setFilePathPrefix(String filePathPrefix) {
        this.filePathPrefix = filePathPrefix;
    }
}

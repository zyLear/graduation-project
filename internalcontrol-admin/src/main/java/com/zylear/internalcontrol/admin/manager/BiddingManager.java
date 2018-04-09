package com.zylear.internalcontrol.admin.manager;

import com.zylear.internalcontrol.admin.bean.BasePageResult;
import com.zylear.internalcontrol.admin.constant.FileDirectory;
import com.zylear.internalcontrol.admin.domain.Project;
import com.zylear.internalcontrol.admin.domain.ProjectBidding;
import com.zylear.internalcontrol.admin.enums.BiddingStatus;
import com.zylear.internalcontrol.admin.enums.ProjectStatus;
import com.zylear.internalcontrol.admin.service.ProjectBiddingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by xiezongyu on 2018/4/9.
 */
@Component
public class BiddingManager {

    private static final Logger logger = LoggerFactory.getLogger(BiddingManager.class);

    private String filePathPrefix;
    private ProjectBiddingService projectBiddingService;


    public BasePageResult saveBidding(String projectNumber, String biddingNumber, String biddingName, String biddingContent, Double prices, MultipartFile file) {

        ProjectBidding projectBidding = projectBiddingService.findByProjectNumberAndBiddingNumber(projectNumber, biddingNumber);
        if (projectBidding != null) {
            return BasePageResult.ID_EXIST_RESPONSE;
        }

        projectBidding = projectBiddingService.findByBiddingNumber(biddingNumber);
        if (projectBidding != null) {
            return BasePageResult.ID_EXIST_RESPONSE;
        }

        projectBidding = projectBiddingService.findByBiddingName(biddingName);
        if (projectBidding != null) {
            return BasePageResult.ID_EXIST_RESPONSE;
        }

        projectBidding = projectBiddingService.findByFilePath(FileDirectory.BIDDING_FILE_DIRECTORY + file.getOriginalFilename());
        if (projectBidding != null) {
            return BasePageResult.FILE_EXIST_RESPONSE;
        }

        try {
            file.transferTo(new File(filePathPrefix + FileDirectory.BIDDING_FILE_DIRECTORY + file.getOriginalFilename()));
            logger.info("save file path:{}", filePathPrefix + FileDirectory.BIDDING_FILE_DIRECTORY + file.getOriginalFilename());
        } catch (IOException e) {
            logger.error("save file error. fileName:{}", file.getOriginalFilename(), e);
            return BasePageResult.UPLOAD_FAIL_RESPONSE;
        }


        /////
        projectBidding = new ProjectBidding();
        projectBidding.setProjectNumber(projectNumber);
        projectBidding.setBiddingNumber(biddingNumber);
        projectBidding.setBiddingName(biddingName);
        projectBidding.setBiddingContent(biddingContent);
        projectBidding.setPrices(prices);
        projectBidding.setFilePath(FileDirectory.PROJECT_FILE_DIRECTORY + file.getOriginalFilename());
        projectBidding.setBiddingStatus(BiddingStatus.close.getValue());
        projectBidding.setIsDeleted(false);
        projectBidding.setCreateTime(new Date());
        projectBidding.setLastUpdateTime(new Date());

        projectBiddingService.insert(projectBidding);
//        projectBidding.s

        return BasePageResult.SUCCESS_RESPONSE;
    }


    @Autowired
    public void setFilePathPrefix(String filePathPrefix) {
        this.filePathPrefix = filePathPrefix;
    }

    @Autowired
    public void setProjectBiddingService(ProjectBiddingService projectBiddingService) {
        this.projectBiddingService = projectBiddingService;
    }

}

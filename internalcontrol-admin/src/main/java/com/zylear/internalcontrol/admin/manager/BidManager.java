package com.zylear.internalcontrol.admin.manager;

import com.zylear.internalcontrol.admin.bean.BasePageResult;
import com.zylear.internalcontrol.admin.constant.FileDirectory;
import com.zylear.internalcontrol.admin.domain.ProjectBid;
import com.zylear.internalcontrol.admin.domain.ProjectBidding;
import com.zylear.internalcontrol.admin.enums.BidStatus;
import com.zylear.internalcontrol.admin.enums.BiddingStatus;
import com.zylear.internalcontrol.admin.service.ProjectBidService;
import com.zylear.internalcontrol.admin.service.ProjectBiddingService;
import com.zylear.internalcontrol.admin.service.ProjectService;
import com.zylear.internalcontrol.admin.util.DateUtil;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by xiezongyu on 2018/4/15.
 */
@Component
public class BidManager {

    private static final Logger logger = LoggerFactory.getLogger(BidManager.class);

    private String filePathPrefix;
    private ProjectService projectService;
    private ProjectBidService projectBidService;
    private ProjectBiddingService projectBiddingService;


    public BasePageResult saveBid(String biddingNumber, String bidCompany, String bidContent, Double bidPrices, MultipartFile file) {
        int retryCount = 5;
        ProjectBid projectBid;
        String bidNumber;
        ProjectBidding projectBidding = projectBiddingService.findByBiddingNumber(biddingNumber);
        if (!BiddingStatus.open.getValue().equals(projectBidding.getBiddingStatus())) {
            return BasePageResult.ERROR_RESPONSE;
        }

        projectBid = projectBidService.findByFilePath(FileDirectory.BID_FILE_DIRECTORY + file.getOriginalFilename());
        if (projectBid != null) {
            return BasePageResult.FILE_EXIST_RESPONSE;
        }

        try {
            file.transferTo(new File(filePathPrefix + FileDirectory.BID_FILE_DIRECTORY + file.getOriginalFilename()));
            logger.info("save file path:{}", filePathPrefix + FileDirectory.BID_FILE_DIRECTORY + file.getOriginalFilename());
        } catch (IOException e) {
            logger.error("save file error. fileName:{}", file.getOriginalFilename(), e);
            return BasePageResult.UPLOAD_FAIL_RESPONSE;
        }

        do {
            bidNumber = DateUtil.formatToYMD(new Date()) + "_" + RandomUtils.nextInt(1000000, 10000000);
            projectBid = projectBidService.findByBidNumber(bidNumber);
        } while (projectBid != null && retryCount-- > 0);

        if (projectBid != null) {
            return BasePageResult.ID_EXIST_RESPONSE;
        }

        projectBid = new ProjectBid();
        projectBid.setBiddingNumber(biddingNumber);
        projectBid.setBidNumber(bidNumber);
        projectBid.setBidCompany(bidCompany);
        projectBid.setBidContent(bidContent);
        projectBid.setBidPrices(bidPrices);
        projectBid.setBidStatus(BidStatus.bided.getValue());
        projectBid.setFilePath(FileDirectory.BID_FILE_DIRECTORY + file.getOriginalFilename());
        projectBid.setIsDeleted(false);
        projectBid.setCreateTime(new Date());
        projectBid.setLastUpdateTime(new Date());
        projectBidService.insert(projectBid);

        return BasePageResult.SUCCESS_RESPONSE;
    }

    public BasePageResult<ProjectBid> queryProjectBids(Integer bidStatus) {
        BasePageResult<ProjectBid> response = BasePageResult.getSuccessResponse();
        response.setData(projectBidService.findByStatus(bidStatus));
        return response;
    }


    @Autowired
    public void setFilePathPrefix(String filePathPrefix) {
        this.filePathPrefix = filePathPrefix;
    }

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Autowired
    public void setProjectBidService(ProjectBidService projectBidService) {
        this.projectBidService = projectBidService;
    }

    @Autowired
    public void setProjectBiddingService(ProjectBiddingService projectBiddingService) {
        this.projectBiddingService = projectBiddingService;
    }


}

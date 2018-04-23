package com.zylear.internalcontrol.admin.manager;

import com.zylear.internalcontrol.admin.bean.*;
import com.zylear.internalcontrol.admin.constant.FileDirectory;
import com.zylear.internalcontrol.admin.domain.Project;
import com.zylear.internalcontrol.admin.domain.ProjectBidding;
import com.zylear.internalcontrol.admin.enums.BiddingStatus;
import com.zylear.internalcontrol.admin.service.ProjectBiddingService;
import com.zylear.internalcontrol.admin.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xiezongyu on 2018/4/9.
 */
@Component
public class BiddingManager {

    private static final Logger logger = LoggerFactory.getLogger(BiddingManager.class);

    private String filePathPrefix;
    private ProjectService projectService;
    private ProjectBiddingService projectBiddingService;


    public BasePageResult saveBidding(String projectNumber, String biddingNumber, String biddingName, Date biddingStartTime, Date biddingEndTime, String biddingContent, Double prices, MultipartFile file) {


        ProjectBidding projectBidding = projectBiddingService.findByBiddingNumber(biddingNumber);
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


        projectBidding = new ProjectBidding();
        projectBidding.setProjectNumber(projectNumber);
        projectBidding.setBiddingNumber(biddingNumber);
        projectBidding.setBiddingName(biddingName);
        projectBidding.setBiddingContent(biddingContent);
        projectBidding.setPrices(prices);
        projectBidding.setFilePath(FileDirectory.PROJECT_FILE_DIRECTORY + file.getOriginalFilename());
        projectBidding.setBiddingStatus(BiddingStatus.close.getValue());
        projectBidding.setBiddingStartTime(biddingStartTime);
        projectBidding.setBiddingEndTime(biddingEndTime);
        projectBidding.setIsDeleted(false);
        projectBidding.setCreateTime(new Date());
        projectBidding.setLastUpdateTime(new Date());

        projectBiddingService.insert(projectBidding);

        return BasePageResult.SUCCESS_RESPONSE;
    }

    public PageResult<BiddingViewBean> getBiddingListPageResult(PageParam pageParam) {
        PageResult<BiddingViewBean> pageResult = new PageResult<>();
        List<ProjectBidding> projectBiddings = projectBiddingService.findByPageParam(pageParam);
        pageResult.setTotal(projectBiddingService.getTotal());
        pageResult.setRows(toBiddingViewBean(projectBiddings));
        return pageResult;
    }

    private List<BiddingViewBean> toBiddingViewBean(List<ProjectBidding> projectBiddings) {
        List<BiddingViewBean> list = new ArrayList<>(projectBiddings.size());
        for (ProjectBidding projectBidding : projectBiddings) {
            Project project = projectService.findByProjectNumber(projectBidding.getProjectNumber());
            if (project == null) {
                continue;
            }
            BiddingViewBean biddingViewBean = new BiddingViewBean();
            biddingViewBean.setId(projectBidding.getId());
            biddingViewBean.setProjectName(project.getProjectName());
            biddingViewBean.setProjectNumber(project.getProjectNumber());
            biddingViewBean.setBiddingNumber(projectBidding.getBiddingNumber());
            biddingViewBean.setBiddingName(projectBidding.getBiddingName());
            biddingViewBean.setBiddingStatus(projectBidding.getBiddingStatus());
            biddingViewBean.setBiddingStartTime(projectBidding.getBiddingStartTime());
            biddingViewBean.setBiddingEndTime(projectBidding.getBiddingEndTime());
            biddingViewBean.setFilePath(projectBidding.getFilePath());
            biddingViewBean.setPrices(projectBidding.getPrices());
            list.add(biddingViewBean);
        }
        return list;
    }

    public BiddingViewBean getBiddingViewBean(String biddingNumber) {
        BiddingViewBean biddingViewBean = new BiddingViewBean();
        ProjectBidding projectBidding = projectBiddingService.findByBiddingNumber(biddingNumber);
        Project project = projectService.findByProjectNumber(projectBidding.getProjectNumber());
        biddingViewBean.setBiddingNumber(biddingNumber);
        biddingViewBean.setBiddingName(biddingNumber);
        biddingViewBean.setProjectNumber(project.getProjectNumber());
        biddingViewBean.setProjectName(project.getProjectName());
        return biddingViewBean;
    }

    public BasePageResult changeBiddingStatus(String biddingNumber, Integer biddingStatus) {
        projectBiddingService.updateStatus(biddingNumber, biddingStatus);
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

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }



}

package com.zylear.internalcontrol.admin.manager;

import com.zylear.internalcontrol.admin.bean.BasePageResult;
import com.zylear.internalcontrol.admin.bean.BidViewBean;
import com.zylear.internalcontrol.admin.bean.PageParam;
import com.zylear.internalcontrol.admin.bean.PageResult;
import com.zylear.internalcontrol.admin.constant.FileDirectory;
import com.zylear.internalcontrol.admin.domain.Project;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            bidNumber = DateUtil.formatToYMDCompact(new Date()) + "_" + RandomUtils.nextInt(1000000, 10000000);
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


    public PageResult<BidViewBean> getBidListPageResult(PageParam pageParam) {
        PageResult<BidViewBean> pageResult = new PageResult<>();
        List<ProjectBid> projectBids = projectBidService.findByPageParam(pageParam);
        pageResult.setTotal(projectBiddingService.getTotal());
        pageResult.setRows(toBidViewBean(projectBids));
        return pageResult;
    }

    private List<BidViewBean> toBidViewBean(List<ProjectBid> projectBids) {
        List<BidViewBean> list = new ArrayList<>(projectBids.size());
        for (ProjectBid bid : projectBids) {
            ProjectBidding projectBidding = projectBiddingService.findByBiddingNumber(bid.getBiddingNumber());
            if (projectBidding == null) {
                continue;
            }
            Project project = projectService.findByProjectNumber(projectBidding.getProjectNumber());
            if (project == null) {
                continue;
            }

            BidViewBean viewBean = new BidViewBean();
            viewBean.setProjectNumber(project.getProjectNumber());
            viewBean.setProjectName(project.getProjectName());
            viewBean.setBiddingNumber(projectBidding.getBiddingNumber());
            viewBean.setBiddingName(projectBidding.getBiddingName());
            viewBean.setBidNumber(bid.getBidNumber());
            viewBean.setBidCompany(bid.getBidCompany());
            viewBean.setBiddingStatus(projectBidding.getBiddingStatus());
            viewBean.setBidStatus(bid.getBidStatus());
            viewBean.setBidPrices(bid.getBidPrices());
            viewBean.setId(bid.getId());
            viewBean.setFilePath(bid.getFilePath());
            list.add(viewBean);
        }
        return list;
    }


    /* <foreach collection="msgIds" open="(" close=")" separator="," index="index" item="msgId">
            #{msgId, jdbcType=INTEGER}
        </foreach>*/


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

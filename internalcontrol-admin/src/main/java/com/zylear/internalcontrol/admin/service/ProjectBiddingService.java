package com.zylear.internalcontrol.admin.service;

import com.zylear.internalcontrol.admin.bean.PageParam;
import com.zylear.internalcontrol.admin.domain.Project;
import com.zylear.internalcontrol.admin.domain.ProjectBidding;

import java.util.List;

/**
 * Created by xiezongyu on 2018/4/9.
 */
public interface ProjectBiddingService {

    ProjectBidding findByBiddingName(String biddingName);

    ProjectBidding findByBiddingNumber(String biddingNumber);

    ProjectBidding findByFilePath(String filePath);

//    ProjectBidding findByProjectNumberAndBiddingNumber(String projectNumber, String biddingNumber);

    void insert(ProjectBidding projectBidding);

    Integer getTotal();

    List<ProjectBidding> findByPageParam(PageParam pageParam);

    void updateStatus(Integer biddingNumber, Integer biddingStatus);

}

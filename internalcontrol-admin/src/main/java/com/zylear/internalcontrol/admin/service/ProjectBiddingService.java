package com.zylear.internalcontrol.admin.service;

import com.zylear.internalcontrol.admin.domain.Project;
import com.zylear.internalcontrol.admin.domain.ProjectBidding;

/**
 * Created by xiezongyu on 2018/4/9.
 */
public interface ProjectBiddingService {

    ProjectBidding findByBiddingName(String biddingName);

    ProjectBidding findByBiddingNumber(String biddingNumber);

    ProjectBidding findByFilePath(String filePath);

    ProjectBidding findByProjectNumberAndBiddingNumber(String projectNumber, String biddingNumber);

    void insert(ProjectBidding projectBidding);
}

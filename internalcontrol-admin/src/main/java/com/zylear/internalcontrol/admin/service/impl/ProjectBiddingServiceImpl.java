package com.zylear.internalcontrol.admin.service.impl;

import com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol.ProjectBiddingMapper;
import com.zylear.internalcontrol.admin.domain.ProjectBidding;
import com.zylear.internalcontrol.admin.service.ProjectBiddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2018/4/9.
 */
@Component
public class ProjectBiddingServiceImpl implements ProjectBiddingService {

    private ProjectBiddingMapper projectBiddingMapper;

    @Override
    public ProjectBidding findByBiddingName(String biddingName) {
        return null;
    }

    @Override
    public ProjectBidding findByBiddingNumber(String biddingNumber) {
        return null;
    }

    @Override
    public ProjectBidding findByFilePath(String filePath) {
        return null;
    }

    @Override
    public ProjectBidding findByProjectNumberAndBiddingNumber(String projectNumber, String biddingNumber) {
        return null;
    }

    @Override
    public void insert(ProjectBidding projectBidding) {
        projectBiddingMapper.insert(projectBidding);
    }

    @Autowired
    public void setProjectBiddingMapper(ProjectBiddingMapper projectBiddingMapper) {
        this.projectBiddingMapper = projectBiddingMapper;
    }
}

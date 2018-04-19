package com.zylear.internalcontrol.admin.service.impl;

import com.zylear.internalcontrol.admin.bean.PageParam;
import com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol.ProjectBiddingMapper;
import com.zylear.internalcontrol.admin.domain.ProjectBidding;
import com.zylear.internalcontrol.admin.service.ProjectBiddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xiezongyu on 2018/4/9.
 */
@Component
public class ProjectBiddingServiceImpl implements ProjectBiddingService {

    private ProjectBiddingMapper projectBiddingMapper;

    @Override
    public ProjectBidding findByBiddingName(String biddingName) {
        return projectBiddingMapper.findByBiddingName(biddingName);
    }

    @Override
    public ProjectBidding findByBiddingNumber(String biddingNumber) {
        return projectBiddingMapper.findByBiddingNumber(biddingNumber);
    }

    @Override
    public ProjectBidding findByFilePath(String filePath) {
        return projectBiddingMapper.findByFilePath(filePath);
    }

//    @Override
//    public ProjectBidding findByProjectNumberAndBiddingNumber(String projectNumber, String biddingNumber) {
//        return null;
//    }

    @Override
    public void insert(ProjectBidding projectBidding) {
        projectBiddingMapper.insert(projectBidding);
    }

    @Override
    public Integer getTotal() {
        return projectBiddingMapper.getTotal();
    }

    @Override
    public List<ProjectBidding> findByPageParam(PageParam pageParam) {
        return projectBiddingMapper.findByPageParam(pageParam);
    }

    @Override
    public void updateStatus(Integer biddingNumber, Integer biddingStatus) {
        projectBiddingMapper.updateStatus(biddingNumber, biddingStatus);
    }

    @Autowired
    public void setProjectBiddingMapper(ProjectBiddingMapper projectBiddingMapper) {
        this.projectBiddingMapper = projectBiddingMapper;
    }
}

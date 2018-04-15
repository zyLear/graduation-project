package com.zylear.internalcontrol.admin.service.impl;

import com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol.ProjectBidMapper;
import com.zylear.internalcontrol.admin.domain.ProjectBid;
import com.zylear.internalcontrol.admin.service.ProjectBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2018/4/15.
 */
@Component
public class ProjectBidServiceImpl implements ProjectBidService {

    private ProjectBidMapper projectBidMapper;

    @Override
    public ProjectBid findByBidNumber(String bidNumber) {
        return projectBidMapper.findByBidNumber(bidNumber);
    }

    @Override
    public void insert(ProjectBid projectBid) {
        projectBidMapper.insert(projectBid);
    }

    @Override
    public ProjectBid findByFilePath(String filePath) {
        return projectBidMapper.findByFilePath(filePath);
    }

    @Autowired
    public void setProjectBidMapper(ProjectBidMapper projectBidMapper) {
        this.projectBidMapper = projectBidMapper;
    }
}

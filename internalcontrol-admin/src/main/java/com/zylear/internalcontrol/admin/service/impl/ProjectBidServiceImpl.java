package com.zylear.internalcontrol.admin.service.impl;

import com.zylear.internalcontrol.admin.bean.PageParam;
import com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol.ProjectBidMapper;
import com.zylear.internalcontrol.admin.domain.ProjectBid;
import com.zylear.internalcontrol.admin.service.ProjectBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public List<ProjectBid> findByStatus(Integer bidStatus) {
        return projectBidMapper.findByStatus(bidStatus);
    }

    @Override
    public List<ProjectBid> findByPageParam(PageParam pageParam) {
        return projectBidMapper.findByPageParam(pageParam);
    }

    @Override
    public List<ProjectBid> findByNumberAndPageParam(String biddingNumber, PageParam pageParam) {
        return projectBidMapper.findByNumberAndPageParam(biddingNumber, pageParam);
    }

    @Override
    public Integer getTotal() {
        return projectBidMapper.getTotal();
    }

    @Override
    public Integer getTotalByBiddingNumber(String biddingNumber) {
        return projectBidMapper.getTotalByBiddingNumber(biddingNumber);
    }

    @Override
    public void updateStatusByBidNumber(String bidNumber, Integer status) {
        projectBidMapper.updateStatusByBidNumber(bidNumber, status);
    }

    @Override
    public void updateStatusByBiddingNumber(String biddingNumber, Integer status) {
        projectBidMapper.updateStatusByBiddingNumber(biddingNumber, status);
    }

    @Override
    public ProjectBid selectByPrimaryKey(Integer id) {
        return projectBidMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ProjectBid> findByAccountAndPageParam(String account, PageParam pageParam) {
        return projectBidMapper.findByAccountAndPageParam(account, pageParam);
    }

    @Override
    public Integer getTotalByAccount(String account) {
        return projectBidMapper.getTotalByAccount(account);
    }

    @Autowired
    public void setProjectBidMapper(ProjectBidMapper projectBidMapper) {
        this.projectBidMapper = projectBidMapper;
    }
}

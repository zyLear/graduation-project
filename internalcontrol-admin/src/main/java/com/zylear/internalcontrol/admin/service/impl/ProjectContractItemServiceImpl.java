package com.zylear.internalcontrol.admin.service.impl;

import com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol.ProjectContractItemMapper;
import com.zylear.internalcontrol.admin.domain.ProjectContractItem;
import com.zylear.internalcontrol.admin.service.ProjectContractItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by xiezongyu on 2018/4/13.
 */
@Component
public class ProjectContractItemServiceImpl implements ProjectContractItemService {

    private ProjectContractItemMapper projectContractItemMapper;

    @Override
    public void insert(ProjectContractItem item) {
        projectContractItemMapper.insert(item);
    }

    @Override
    public List<ProjectContractItem> findByContractNumber(String contractNumber) {
        return projectContractItemMapper.findByContractNumber(contractNumber);
    }

    @Override
    public void updateFinishDay(Integer itemId, Date date) {
        projectContractItemMapper.updateFinishDay(itemId, date);
    }

    @Override
    public ProjectContractItem findById(Integer itemId) {
        return projectContractItemMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public Integer findUnfinishCount(Integer itemId) {
        return projectContractItemMapper.findUnfinishCount(itemId);
    }

    @Autowired
    public void setProjectContractItemMapper(ProjectContractItemMapper projectContractItemMapper) {
        this.projectContractItemMapper = projectContractItemMapper;
    }
}

package com.zylear.internalcontrol.admin.service.impl;

import com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol.ProjectContractItemMapper;
import com.zylear.internalcontrol.admin.domain.ProjectContractItem;
import com.zylear.internalcontrol.admin.service.ProjectContractItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Autowired
    public void setProjectContractItemMapper(ProjectContractItemMapper projectContractItemMapper) {
        this.projectContractItemMapper = projectContractItemMapper;
    }
}

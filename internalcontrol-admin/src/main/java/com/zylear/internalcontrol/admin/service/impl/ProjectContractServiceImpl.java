package com.zylear.internalcontrol.admin.service.impl;

import com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol.ProjectContractMapper;
import com.zylear.internalcontrol.admin.domain.ProjectContract;
import com.zylear.internalcontrol.admin.service.ProjectContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2018/4/11.
 */
@Component
public class ProjectContractServiceImpl implements ProjectContractService {

    private ProjectContractMapper projectContractMapper;


    @Override
    public void insert(ProjectContract projectContract) {

    }

    @Override
    public ProjectContract findByContractName(String contractName) {
        return null;
    }

    @Override
    public ProjectContract findByContractNumber(String contractNumber) {
        return null;
    }

    @Override
    public ProjectContract findByFilePath(String filePath) {
        return null;
    }



    @Autowired
    public void setProjectContractMapper(ProjectContractMapper projectContractMapper) {
        this.projectContractMapper = projectContractMapper;
    }
}

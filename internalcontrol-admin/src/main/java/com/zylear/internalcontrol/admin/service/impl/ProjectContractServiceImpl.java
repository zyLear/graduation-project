package com.zylear.internalcontrol.admin.service.impl;

import com.zylear.internalcontrol.admin.bean.PageParam;
import com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol.ProjectContractMapper;
import com.zylear.internalcontrol.admin.domain.ProjectContract;
import com.zylear.internalcontrol.admin.service.ProjectContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by xiezongyu on 2018/4/11.
 */
@Component
public class ProjectContractServiceImpl implements ProjectContractService {

    private ProjectContractMapper projectContractMapper;


    @Override
    public void insert(ProjectContract projectContract) {
        projectContractMapper.insert(projectContract);
    }

    @Override
    public ProjectContract findByContractName(String contractName) {
        return projectContractMapper.findByContractName(contractName);
    }

    @Override
    public ProjectContract findByContractNumber(String contractNumber) {
        return projectContractMapper.findByContractNumber(contractNumber);
    }

    @Override
    public ProjectContract findByFilePath(String filePath) {
        return projectContractMapper.findByFilePath(filePath);
    }

    @Override
    public List<ProjectContract> findByStatus(Integer contractStatus) {
        return projectContractMapper.findByStatus(contractStatus);
    }

    @Override
    public List<ProjectContract> findByPageParam(PageParam pageParam) {
        return projectContractMapper.findByPageParam(pageParam);
    }

    @Override
    public Integer getTotal() {
        return projectContractMapper.getTotal();
    }

    @Override
    public void updateStatusAndFinishDay(String contractNumber, Integer contractStatus, Date date) {
        projectContractMapper.updateStatusAndFinishDay(contractNumber, contractStatus, date);
    }


    @Autowired
    public void setProjectContractMapper(ProjectContractMapper projectContractMapper) {
        this.projectContractMapper = projectContractMapper;
    }
}

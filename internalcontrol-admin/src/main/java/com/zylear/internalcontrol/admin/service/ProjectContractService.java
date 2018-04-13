package com.zylear.internalcontrol.admin.service;

import com.zylear.internalcontrol.admin.domain.ProjectContract;

/**
 * Created by xiezongyu on 2018/4/11.
 */
public interface ProjectContractService {

    void insert(ProjectContract projectContract);

    ProjectContract findByContractName(String contractName);

    ProjectContract findByContractNumber(String contractNumber);

    ProjectContract findByFilePath(String filePath);
}

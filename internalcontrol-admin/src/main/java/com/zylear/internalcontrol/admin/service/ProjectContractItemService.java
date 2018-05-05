package com.zylear.internalcontrol.admin.service;

import com.zylear.internalcontrol.admin.domain.ProjectContractItem;

import java.util.Date;
import java.util.List;

/**
 * Created by xiezongyu on 2018/4/13.
 */
public interface ProjectContractItemService {

    void insert(ProjectContractItem item);

    List<ProjectContractItem> findByContractNumber(String contractNumber);

    void updateFinishDay(Integer itemId, Date date);

    ProjectContractItem findById(Integer itemId);

    Integer findUnfinishCount(String contractNumber);
}

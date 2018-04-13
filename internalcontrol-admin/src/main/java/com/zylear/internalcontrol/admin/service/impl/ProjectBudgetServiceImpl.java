package com.zylear.internalcontrol.admin.service.impl;

import com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol.ProjectBudgetMapper;
import com.zylear.internalcontrol.admin.domain.ProjectBudget;
import com.zylear.internalcontrol.admin.service.ProjectBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2018/4/9.
 */
@Component
public class ProjectBudgetServiceImpl implements ProjectBudgetService {

    private ProjectBudgetMapper projectBudgetMapper;

    @Override
    public void insert(ProjectBudget projectBudget) {
        projectBudgetMapper.insert(projectBudget);
    }

    @Override
    public ProjectBudget findByNumberAndAspect(String projectNumber, String budgetAspect) {
        return null;
    }

    @Autowired
    public void setProjectBudgetMapper(ProjectBudgetMapper projectBudgetMapper) {
        this.projectBudgetMapper = projectBudgetMapper;
    }
}

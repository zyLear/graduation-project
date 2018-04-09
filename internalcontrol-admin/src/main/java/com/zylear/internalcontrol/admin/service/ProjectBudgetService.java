package com.zylear.internalcontrol.admin.service;

import com.zylear.internalcontrol.admin.domain.ProjectBudget;

/**
 * Created by xiezongyu on 2018/4/9.
 */
public interface ProjectBudgetService {

    void insert(ProjectBudget projectBudget);

    ProjectBudget findByNumberAndAspect(String projectNumber, String budgetAspect);
}

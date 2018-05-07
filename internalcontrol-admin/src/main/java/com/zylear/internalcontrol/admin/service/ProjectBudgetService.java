package com.zylear.internalcontrol.admin.service;

import com.zylear.internalcontrol.admin.bean.PageParam;
import com.zylear.internalcontrol.admin.domain.ProjectBudget;

import java.util.List;

/**
 * Created by xiezongyu on 2018/4/9.
 */
public interface ProjectBudgetService {

    void insert(ProjectBudget projectBudget);

    List<ProjectBudget> findByProjectNumber(String projectNumber);

    List<ProjectBudget> findByPageParam(PageParam pageParam);

    int getTotal();

    ProjectBudget selectByPrimaryKey(Integer id);

    Double findTotalPricesByProjectNumber(String projectNumber);

//    ProjectBudget findByNumberAndAspect(String projectNumber, String budgetAspect);
}


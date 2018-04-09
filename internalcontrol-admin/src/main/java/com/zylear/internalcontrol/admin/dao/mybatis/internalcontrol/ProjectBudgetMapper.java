package com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol;

import com.zylear.internalcontrol.admin.domain.ProjectBudget;

public interface ProjectBudgetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectBudget record);

    int insertSelective(ProjectBudget record);

    ProjectBudget selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectBudget record);

    int updateByPrimaryKeyWithBLOBs(ProjectBudget record);

    int updateByPrimaryKey(ProjectBudget record);
}
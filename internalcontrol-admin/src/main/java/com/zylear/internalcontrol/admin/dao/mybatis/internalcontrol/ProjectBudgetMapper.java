package com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol;

import com.zylear.internalcontrol.admin.bean.PageParam;
import com.zylear.internalcontrol.admin.domain.ProjectBudget;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectBudgetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectBudget record);

    int insertSelective(ProjectBudget record);

    ProjectBudget selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectBudget record);

    int updateByPrimaryKeyWithBLOBs(ProjectBudget record);

    int updateByPrimaryKey(ProjectBudget record);


    List<ProjectBudget> findByProjectNumber(@Param("projectNumber") String projectNumber);

    List<ProjectBudget> findByPageParam(@Param("pageParam") PageParam pageParam);

    int getTotal();
}
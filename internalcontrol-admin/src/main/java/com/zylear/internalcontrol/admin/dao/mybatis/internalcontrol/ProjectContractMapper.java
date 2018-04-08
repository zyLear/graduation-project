package com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol;

import com.zylear.internalcontrol.admin.domain.ProjectContract;

public interface ProjectContractMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectContract record);

    int insertSelective(ProjectContract record);

    ProjectContract selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectContract record);

    int updateByPrimaryKeyWithBLOBs(ProjectContract record);

    int updateByPrimaryKey(ProjectContract record);
}
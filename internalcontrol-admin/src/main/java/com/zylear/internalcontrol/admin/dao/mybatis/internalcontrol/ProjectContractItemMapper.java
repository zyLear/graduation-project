package com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol;

import com.zylear.internalcontrol.admin.domain.ProjectContractItem;

public interface ProjectContractItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectContractItem record);

    int insertSelective(ProjectContractItem record);

    ProjectContractItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectContractItem record);

    int updateByPrimaryKeyWithBLOBs(ProjectContractItem record);

    int updateByPrimaryKey(ProjectContractItem record);
}
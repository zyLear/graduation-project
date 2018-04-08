package com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol;

import com.zylear.internalcontrol.admin.domain.ProjectBid;

public interface ProjectBidMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectBid record);

    int insertSelective(ProjectBid record);

    ProjectBid selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectBid record);

    int updateByPrimaryKeyWithBLOBs(ProjectBid record);

    int updateByPrimaryKey(ProjectBid record);
}
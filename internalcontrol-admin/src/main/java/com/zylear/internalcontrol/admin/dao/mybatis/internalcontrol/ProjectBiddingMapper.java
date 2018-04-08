package com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol;

import com.zylear.internalcontrol.admin.domain.ProjectBidding;

public interface ProjectBiddingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectBidding record);

    int insertSelective(ProjectBidding record);

    ProjectBidding selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectBidding record);

    int updateByPrimaryKeyWithBLOBs(ProjectBidding record);

    int updateByPrimaryKey(ProjectBidding record);
}
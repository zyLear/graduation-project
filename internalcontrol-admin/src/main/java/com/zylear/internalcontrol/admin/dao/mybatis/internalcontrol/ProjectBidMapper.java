package com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol;

import com.zylear.internalcontrol.admin.domain.ProjectBid;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectBidMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectBid record);

    int insertSelective(ProjectBid record);

    ProjectBid selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectBid record);

    int updateByPrimaryKeyWithBLOBs(ProjectBid record);

    int updateByPrimaryKey(ProjectBid record);


    ProjectBid findByBidNumber(@Param("bidNumber") String bidNumber);

    ProjectBid findByFilePath(@Param("filePath") String filePath);

    List<ProjectBid> findByStatus(@Param("bidStatus") Integer bidStatus);
}
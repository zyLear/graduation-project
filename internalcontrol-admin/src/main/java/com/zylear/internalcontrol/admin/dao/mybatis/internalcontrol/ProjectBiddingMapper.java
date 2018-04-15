package com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol;

import com.zylear.internalcontrol.admin.bean.PageParam;
import com.zylear.internalcontrol.admin.domain.ProjectBidding;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectBiddingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectBidding record);

    int insertSelective(ProjectBidding record);

    ProjectBidding selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectBidding record);

    int updateByPrimaryKeyWithBLOBs(ProjectBidding record);

    int updateByPrimaryKey(ProjectBidding record);


    ProjectBidding findByBiddingName(@Param("biddingName") String biddingName);

    ProjectBidding findByBiddingNumber(@Param("biddingNumber") String biddingNumber);

    ProjectBidding findByFilePath(@Param("filePath") String filePath);

    List<ProjectBidding> findByPageParam(@Param("pageParam") PageParam pageParam);

    Integer getTotal();
}
package com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol;

import com.zylear.internalcontrol.admin.domain.ProjectContractItem;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ProjectContractItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectContractItem record);

    int insertSelective(ProjectContractItem record);

    ProjectContractItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectContractItem record);

    int updateByPrimaryKeyWithBLOBs(ProjectContractItem record);

    int updateByPrimaryKey(ProjectContractItem record);


    List<ProjectContractItem> findByContractNumber(@Param("contractNumber") String contractNumber);

    void updateFinishDay(@Param("id") Integer id,
                         @Param("date") Date date);

    Integer findUnfinishCount(@Param("id") Integer id);
}
package com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol;

import com.zylear.internalcontrol.admin.domain.ProjectContract;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectContractMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectContract record);

    int insertSelective(ProjectContract record);

    ProjectContract selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectContract record);

    int updateByPrimaryKeyWithBLOBs(ProjectContract record);

    int updateByPrimaryKey(ProjectContract record);


    ProjectContract findByContractName(@Param("contractName") String contractName);

    ProjectContract findByContractNumber(@Param("contractNumber")String contractNumber);

    ProjectContract findByFilePath(@Param("filePath")String filePath);

    List<ProjectContract> findByStatus(@Param("contractStatus") Integer contractStatus);
}
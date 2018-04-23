package com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol;

import com.zylear.internalcontrol.admin.bean.PageParam;
import com.zylear.internalcontrol.admin.domain.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKeyWithBLOBs(Project record);

    int updateByPrimaryKey(Project record);


    Project findByFilePath(@Param("filePath") String filePath);

    Project findByProjectName(@Param("projectName")String projectName);

    Project findByProjectNumber(@Param("projectNumber")String projectNumber);

    void updateStatus(@Param("projectNumber")String projectNumber,
                      @Param("projectStatus")Integer status);

    List<Project> findByStatus(@Param("projectStatus")Integer projectStatus);

    List<Project> findByPageParam(@Param("pageParam") PageParam pageParam);

    Integer getTotal();
}
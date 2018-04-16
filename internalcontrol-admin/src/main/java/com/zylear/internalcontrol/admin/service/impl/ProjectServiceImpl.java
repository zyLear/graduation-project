package com.zylear.internalcontrol.admin.service.impl;

import com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol.ProjectMapper;
import com.zylear.internalcontrol.admin.domain.Project;
import com.zylear.internalcontrol.admin.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xiezongyu on 2018/4/9.
 */
@Component
public class ProjectServiceImpl implements ProjectService {

    private ProjectMapper projectMapper;

    @Override
    public Project findByFilePath(String filePath) {
        return projectMapper.findByFilePath(filePath);
    }

    @Override
    public Project findByProjectName(String projectName) {
        return projectMapper.findByProjectName(projectName);
    }

    @Override
    public Project findByProjectNumber(String projectNumber) {
        return projectMapper.findByProjectNumber(projectNumber);
    }

    @Override
    public void insert(Project project) {
        projectMapper.insert(project);
    }

    @Override
    public void updateStatus(String projectNumber, Integer status) {
        projectMapper.updateStatus(projectNumber, status);
    }

    @Override
    public List<Project> findByStatus(Integer projectStatus) {
        return projectMapper.findByStatus(projectStatus);
    }

    @Override
    public void update(Project needUpdateProject) {
        projectMapper.updateByPrimaryKeySelective(needUpdateProject);
    }


    @Autowired
    public void setProjectMapper(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }
}

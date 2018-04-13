package com.zylear.internalcontrol.admin.service.impl;

import com.zylear.internalcontrol.admin.dao.mybatis.internalcontrol.ProjectMapper;
import com.zylear.internalcontrol.admin.domain.Project;
import com.zylear.internalcontrol.admin.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiezongyu on 2018/4/9.
 */
@Component
public class ProjectServiceImpl implements ProjectService {

    private ProjectMapper projectMapper;

    @Override
    public Project findByFilePath(String filePath) {
        return null;
    }

    @Override
    public Project findByProjectName(String projectName) {
        return null;
    }

    @Override
    public Project findByProjectNumber(String projectNumber) {
        return null;
    }

    @Override
    public void insert(Project project) {
        projectMapper.insert(project);
    }



    @Autowired
    public void setProjectMapper(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }
}

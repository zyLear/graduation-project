package com.zylear.internalcontrol.admin.service;

import com.zylear.internalcontrol.admin.domain.Project;

import java.util.List;

/**
 * Created by xiezongyu on 2018/4/9.
 */
public interface ProjectService {
    Project findByFilePath(String filePath);

    Project findByProjectName(String projectName);

    Project findByProjectNumber(String projectNumber);

    void insert(Project project);

    void updateStatus(String projectNumber, Integer status);

    List<Project> findByStatus(Integer projectStatus);

    void update(Project needUpdateProject);
}

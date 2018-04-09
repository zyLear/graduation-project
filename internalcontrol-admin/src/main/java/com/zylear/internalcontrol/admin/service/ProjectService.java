package com.zylear.internalcontrol.admin.service;

import com.zylear.internalcontrol.admin.domain.Project;

/**
 * Created by xiezongyu on 2018/4/9.
 */
public interface ProjectService {
    Project findByFilePath(String s);

    Project findByProjectName(String projectName);

    Project findByProjectNumber(String projectNumber);

    void insert(Project project);
}

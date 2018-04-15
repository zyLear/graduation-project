package com.zylear.internalcontrol.admin.manager;

import com.zylear.internalcontrol.admin.bean.BasePageResult;
import com.zylear.internalcontrol.admin.constant.FileDirectory;
import com.zylear.internalcontrol.admin.controller.ProjectController;
import com.zylear.internalcontrol.admin.domain.Project;
import com.zylear.internalcontrol.admin.enums.ProjectStatus;
import com.zylear.internalcontrol.admin.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by xiezongyu on 2018/4/9.
 */
@Component
public class ProjectManager {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    private String filePathPrefix;
    private ProjectService projectService;


    public BasePageResult saveProjectApplication(String projectNumber, String projectName, String applicant, String applicationDepartment, String projectContent, Double projectBudget, MultipartFile file) {

        Project project = projectService.findByProjectNumber(projectNumber);
        if (project != null) {
            return BasePageResult.ID_EXIST_RESPONSE;
        }

        project = projectService.findByProjectName(projectName);
        if (project != null) {
            return BasePageResult.ID_EXIST_RESPONSE;
        }

        project = projectService.findByFilePath(FileDirectory.PROJECT_FILE_DIRECTORY + file.getOriginalFilename());
        if (project != null) {
            return BasePageResult.FILE_EXIST_RESPONSE;
        }

        try {
            file.transferTo(new File(filePathPrefix + FileDirectory.PROJECT_FILE_DIRECTORY + file.getOriginalFilename()));
            logger.info("save file path:{}", filePathPrefix + FileDirectory.PROJECT_FILE_DIRECTORY + file.getOriginalFilename());
        } catch (IOException e) {
            logger.error("save file error. fileName:{}", file.getOriginalFilename(), e);
            return BasePageResult.UPLOAD_FAIL_RESPONSE;
        }

        project = new Project();
        project.setProjectNumber(projectNumber);
        project.setProjectName(projectName);
        project.setApplicant(applicant);
        project.setApplicationDepartment(applicationDepartment);
        project.setProjectContent(projectContent);
        project.setProjectBudget(projectBudget);
        project.setFilePath(FileDirectory.PROJECT_FILE_DIRECTORY + file.getOriginalFilename());
        project.setIsDeleted(false);
        project.setCreateTime(new Date());
        project.setLastUpdateTime(new Date());
        project.setProjectStatus(ProjectStatus.in_approval.getValue());
        projectService.insert(project);

        return BasePageResult.SUCCESS_RESPONSE;
    }

    public BasePageResult<Project> queryProjects(Integer projectStatus) {
        BasePageResult<Project> response = BasePageResult.getSuccessResponse();
        response.setData(projectService.findByStatus(projectStatus));
        return response;
    }

    public BasePageResult<Project> sureApproval(String projectNumber, Integer projectStatus, String approvalComment) {

        Project project = projectService.findByProjectNumber(projectNumber);
        if (project == null) {
            return BasePageResult.PROJECT_NO_EXIST_RESPONSE;
        }
        if (!ProjectStatus.in_approval.getValue().equals(project.getProjectStatus())) {
            return BasePageResult.ERROR_RESPONSE;
        }
        Project needUpdateProject = new Project();
        needUpdateProject.setId(project.getId());
        needUpdateProject.setProjectStatus(projectStatus);
        needUpdateProject.setApprovalComment(approvalComment);
        projectService.update(needUpdateProject);
        return BasePageResult.SUCCESS_RESPONSE;
    }


    @Autowired
    public void setFilePathPrefix(String filePathPrefix) {
        this.filePathPrefix = filePathPrefix;
    }

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }


}

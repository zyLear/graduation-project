package com.zylear.internalcontrol.admin.bean;

import java.util.Date;
import java.util.List;

/**
 * Created by xiezongyu on 2018/4/22.
 */
public class ProjectViewBean {

    private Integer id;

    private String projectNumber;

    private String projectName;

    private String applicant;

    private String applicationDepartment;

    private Double projectBudget;

    private Integer projectStatus;

    private String approvalComment;

    private String filePath;

    private List<BudgetViewBean> items;

    private String projectContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getApplicationDepartment() {
        return applicationDepartment;
    }

    public void setApplicationDepartment(String applicationDepartment) {
        this.applicationDepartment = applicationDepartment;
    }

    public Double getProjectBudget() {
        return projectBudget;
    }

    public void setProjectBudget(Double projectBudget) {
        this.projectBudget = projectBudget;
    }

    public Integer getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Integer projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getApprovalComment() {
        return approvalComment;
    }

    public void setApprovalComment(String approvalComment) {
        this.approvalComment = approvalComment;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<BudgetViewBean> getItems() {
        return items;
    }

    public void setItems(List<BudgetViewBean> items) {
        this.items = items;
    }

    public String getProjectContent() {
        return projectContent;
    }

    public void setProjectContent(String projectContent) {
        this.projectContent = projectContent;
    }
}

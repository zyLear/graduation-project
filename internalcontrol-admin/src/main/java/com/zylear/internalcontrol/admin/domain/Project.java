package com.zylear.internalcontrol.admin.domain;

import java.util.Date;

public class Project {
    private Integer id;

    private String projectNumber;

    private String projectName;

    private String applicant;

    private String applicationDepartment;

    private Double projectBudget;

    private Byte approvalResult;

    private String approvalComment;

    private String filePath;

    private Byte isDeleted;

    private Date createTime;

    private Date lastUpdateTime;

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

    public Byte getApprovalResult() {
        return approvalResult;
    }

    public void setApprovalResult(Byte approvalResult) {
        this.approvalResult = approvalResult;
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

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getProjectContent() {
        return projectContent;
    }

    public void setProjectContent(String projectContent) {
        this.projectContent = projectContent;
    }
}
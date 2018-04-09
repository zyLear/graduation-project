package com.zylear.internalcontrol.admin.domain;

import java.util.Date;

public class ProjectBudget {
    private Integer id;

    private String projectNumber;

    private String budgetAspect;

    private Double budgetMoney;

    private Boolean isDeleted;

    private Date createTime;

    private Date lastUpdateTime;

    private String budgetContent;

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

    public String getBudgetAspect() {
        return budgetAspect;
    }

    public void setBudgetAspect(String budgetAspect) {
        this.budgetAspect = budgetAspect;
    }

    public Double getBudgetMoney() {
        return budgetMoney;
    }

    public void setBudgetMoney(Double budgetMoney) {
        this.budgetMoney = budgetMoney;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
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

    public String getBudgetContent() {
        return budgetContent;
    }

    public void setBudgetContent(String budgetContent) {
        this.budgetContent = budgetContent;
    }
}
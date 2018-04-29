package com.zylear.internalcontrol.admin.bean;

import java.util.Date;

/**
 * Created by xiezongyu on 2018/4/25.
 */
public class BudgetViewBean {

    private String projectNumber;
    private String projectName;

    private String budgetAspect;
    private String budgetContent;
    private Double budgetMoney;

    private Date createTime;

    private Integer id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBudgetAspect() {
        return budgetAspect;
    }

    public void setBudgetAspect(String budgetAspect) {
        this.budgetAspect = budgetAspect;
    }

    public String getBudgetContent() {
        return budgetContent;
    }

    public void setBudgetContent(String budgetContent) {
        this.budgetContent = budgetContent;
    }

    public Double getBudgetMoney() {
        return budgetMoney;
    }

    public void setBudgetMoney(Double budgetMoney) {
        this.budgetMoney = budgetMoney;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

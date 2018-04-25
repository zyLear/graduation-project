package com.zylear.internalcontrol.admin.bean;

/**
 * Created by xiezongyu on 2018/4/25.
 */
public class BudgetViewBean {

    private String budgetAspect;
    private String budgetContent;
    private Double budgetMoney;

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
}

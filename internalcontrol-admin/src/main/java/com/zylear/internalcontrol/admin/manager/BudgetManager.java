package com.zylear.internalcontrol.admin.manager;

import com.zylear.internalcontrol.admin.bean.BasePageResult;
import com.zylear.internalcontrol.admin.controller.ProjectController;
import com.zylear.internalcontrol.admin.domain.ProjectBudget;
import com.zylear.internalcontrol.admin.service.ProjectBudgetService;
import com.zylear.internalcontrol.admin.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by xiezongyu on 2018/4/9.
 */
@Component
public class BudgetManager {

    private static final Logger logger = LoggerFactory.getLogger(BudgetManager.class);

    private ProjectBudgetService projectBudgetService;


    public BasePageResult saveBudget(String projectNumber, String budgetAspect, String budgetContent, Double budgetMoney) {
        ProjectBudget projectBudget = projectBudgetService.findByNumberAndAspect(projectNumber, budgetAspect);
        if (projectBudget != null) {
            return BasePageResult.ID_EXIST_RESPONSE;
        }
        projectBudget = new ProjectBudget();
        projectBudget.setProjectNumber(projectNumber);
        projectBudget.setBudgetAspect(budgetAspect);
        projectBudget.setBudgetContent(budgetContent);
        projectBudget.setBudgetMoney(budgetMoney);
        projectBudget.setIsDeleted(false);
        projectBudget.setCreateTime(new Date());
        projectBudget.setLastUpdateTime(new Date());
        projectBudgetService.insert(projectBudget);

        return BasePageResult.SUCCESS_RESPONSE;
    }




    @Autowired
    public void setProjectBudgetService(ProjectBudgetService projectBudgetService) {
        this.projectBudgetService = projectBudgetService;
    }
}

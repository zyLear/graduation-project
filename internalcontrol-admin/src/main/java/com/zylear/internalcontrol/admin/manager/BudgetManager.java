package com.zylear.internalcontrol.admin.manager;

import com.zylear.internalcontrol.admin.bean.BasePageResult;
import com.zylear.internalcontrol.admin.bean.BudgetViewBean;
import com.zylear.internalcontrol.admin.bean.PageParam;
import com.zylear.internalcontrol.admin.bean.PageResult;
import com.zylear.internalcontrol.admin.config.DataSourceInternalControlConfig;
import com.zylear.internalcontrol.admin.domain.Project;
import com.zylear.internalcontrol.admin.domain.ProjectBudget;
import com.zylear.internalcontrol.admin.enums.ProjectStatus;
import com.zylear.internalcontrol.admin.service.ProjectBudgetService;
import com.zylear.internalcontrol.admin.service.ProjectService;
import com.zylear.internalcontrol.admin.util.DateUtil;
import com.zylear.internalcontrol.admin.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by xiezongyu on 2018/4/9.
 */
@Component
public class BudgetManager {

    private static final Logger logger = LoggerFactory.getLogger(BudgetManager.class);

    private ProjectBudgetService projectBudgetService;
    private ProjectService projectService;


//    public BasePageResult saveBudget(String projectNumber, String budgetAspect, String budgetContent, Double budgetMoney) {
//        ProjectBudget projectBudget = projectBudgetService.findByNumberAndAspect(projectNumber, budgetAspect);
//        if (projectBudget != null) {
//            return BasePageResult.ID_EXIST_RESPONSE;
//        }
//        projectBudget = new ProjectBudget();
//        projectBudget.setProjectNumber(projectNumber);
//        projectBudget.setBudgetAspect(budgetAspect);
//        projectBudget.setBudgetContent(budgetContent);
//        projectBudget.setBudgetMoney(budgetMoney);
//        projectBudget.setIsDeleted(false);
//        projectBudget.setCreateTime(new Date());
//        projectBudget.setLastUpdateTime(new Date());
//        projectBudgetService.insert(projectBudget);
//
//        return BasePageResult.SUCCESS_RESPONSE;
//    }

    @Transactional(value = DataSourceInternalControlConfig.TX_MANAGER)
    public BasePageResult saveBudget(String projectNumber, String items) {
        List<ProjectBudget> projectBudgetItems;
        try {
            projectBudgetItems = JsonUtil.parseJsonToList(items, ProjectBudget.class);
        } catch (Exception e) {
            logger.error("parse json to list fail. string:{}", items, e);
            return BasePageResult.ERROR_RESPONSE;
        }
//        System.out.println("test:" + projectBudgetItems);

        Project project = projectService.findByProjectNumber(projectNumber);
        if (project == null) {
            return BasePageResult.PROJECT_NO_EXIST_RESPONSE;
        }

        if (!ProjectStatus.budgeting.getValue().equals(project.getProjectStatus())) {
            return BasePageResult.ERROR_RESPONSE;
        }

        double total = 0.0;
        for (ProjectBudget projectBudget : projectBudgetItems) {
            total += projectBudget.getBudgetMoney();
        }
        if (total > project.getProjectBudget()) {
            return BasePageResult.OVERSPEND_RESPONSE;
        }

        projectService.updateStatus(projectNumber, ProjectStatus.bidding.getValue());

        for (ProjectBudget projectBudget : projectBudgetItems) {
            projectBudget.setProjectNumber(projectNumber);
            projectBudget.setIsDeleted(false);
            projectBudget.setCreateTime(new Date());
            projectBudget.setLastUpdateTime(new Date());
            projectBudgetService.insert(projectBudget);
        }

        return BasePageResult.SUCCESS_RESPONSE;
    }


    public PageResult<BudgetViewBean> getBudgetListPageResult(PageParam pageParam) {

        PageResult<BudgetViewBean> pageResult = new PageResult<>();
        List<ProjectBudget> budgets = projectBudgetService.findByPageParam(pageParam);
        pageResult.setTotal(projectBudgetService.getTotal());
        pageResult.setRows(toBudgetViewBean(budgets));
        return pageResult;

    }

    private List<BudgetViewBean> toBudgetViewBean(List<ProjectBudget> budgets) {
        List<BudgetViewBean> list = new ArrayList<>(budgets.size());
        for (ProjectBudget budget : budgets) {
            Project project = projectService.findByProjectNumber(budget.getProjectNumber());
            if (project == null) {
                continue;
            }
            BudgetViewBean budgetViewBean = new BudgetViewBean();
            budgetViewBean.setId(budget.getId());
            budgetViewBean.setProjectNumber(project.getProjectNumber());
            budgetViewBean.setProjectName(project.getProjectName());
            budgetViewBean.setBudgetAspect(budget.getBudgetAspect());
            budgetViewBean.setBudgetMoney(budget.getBudgetMoney());
            budgetViewBean.setBudgetContent(budget.getBudgetContent());
            budgetViewBean.setCreateTime(budget.getCreateTime());
            list.add(budgetViewBean);
        }
        return list;
    }


    public BasePageResult<BudgetViewBean> getBudgetContent(Integer id) {
        ProjectBudget budget = projectBudgetService.selectByPrimaryKey(id);
        if (budget == null) {
            return BasePageResult.BUDGET_NO_EXIST_RESPONSE;
        }
        BasePageResult<BudgetViewBean> successResponse = BasePageResult.getSuccessResponse();
        BudgetViewBean budgetViewBean = new BudgetViewBean();
        budgetViewBean.setBudgetContent(budget.getBudgetContent());
        successResponse.setData(Arrays.asList(budgetViewBean));
        return successResponse;
    }


    @Autowired
    public void setProjectBudgetService(ProjectBudgetService projectBudgetService) {
        this.projectBudgetService = projectBudgetService;
    }

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }


}

package com.zylear.internalcontrol.admin.manager;

import com.zylear.internalcontrol.admin.bean.*;
import com.zylear.internalcontrol.admin.config.DataSourceInternalControlConfig;
import com.zylear.internalcontrol.admin.constant.FileDirectory;
import com.zylear.internalcontrol.admin.domain.*;
import com.zylear.internalcontrol.admin.enums.BidStatus;
import com.zylear.internalcontrol.admin.enums.ContractStatus;
import com.zylear.internalcontrol.admin.service.*;
import com.zylear.internalcontrol.admin.util.DateUtil;
import com.zylear.internalcontrol.admin.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by xiezongyu on 2018/4/11.
 */
@Component
public class ContractManager {

    private static final Logger logger = LoggerFactory.getLogger(ContractManager.class);

    private String filePathPrefix;
    private ProjectContractService projectContractService;
    private ProjectContractItemService projectContractItemService;
    private ProjectBidService projectBidService;
    private ProjectBiddingService projectBiddingService;
    private ProjectService projectService;

    @Transactional(value = DataSourceInternalControlConfig.TX_MANAGER)
    public BasePageResult saveContract(String bidNumber, String contractNumber, String contractName, String contractContent, MultipartFile file, String items) {

        List<ProjectContractItem> projectContractItems;
        try {
            projectContractItems = JsonUtil.parseJsonToList(items, ProjectContractItem.class);
        } catch (Exception e) {
            logger.error("parse json to list fail. string:{}", items, e);
            return BasePageResult.ERROR_RESPONSE;
        }

        if (projectContractItems == null || projectContractItems.isEmpty()) {
            logger.info("items is empty");
            return BasePageResult.ERROR_RESPONSE;
        }

        ProjectContract projectContract = projectContractService.findByContractNumber(contractNumber);
//        System.out.println("test:"+projectContract.getCreateTime());
        if (projectContract != null) {
            return BasePageResult.ID_EXIST_RESPONSE;
        }
        projectContract = projectContractService.findByContractName(contractName);
        if (projectContract != null) {
            return BasePageResult.ID_EXIST_RESPONSE;
        }
        projectContract = projectContractService.findByFilePath(FileDirectory.CONTRACT_FILE_DIRECTORY + file.getOriginalFilename());
        if (projectContract != null) {
            return BasePageResult.FILE_EXIST_RESPONSE;
        }

        ProjectBid bid = projectBidService.findByBidNumber(bidNumber);
        if (bid == null) {
            return BasePageResult.BID_NO_EXIST_RESPONSE;
        }
        if (!BidStatus.winning.getValue().equals(bid.getBidStatus())) {
            return BasePageResult.ERROR_RESPONSE;
        }

        Double money = 0.0;

        for (ProjectContractItem item : projectContractItems) {
            money += item.getItemMoney();
        }

        if (money > bid.getBidPrices()) {
            return BasePageResult.OVERSPEND_RESPONSE;
        }


        try {
            file.transferTo(new File(filePathPrefix + FileDirectory.CONTRACT_FILE_DIRECTORY + file.getOriginalFilename()));
            logger.info("save file path:{}", filePathPrefix + FileDirectory.CONTRACT_FILE_DIRECTORY + file.getOriginalFilename());
        } catch (IOException e) {
            logger.error("save file error. fileName:{}", file.getOriginalFilename(), e);
            return BasePageResult.UPLOAD_FAIL_RESPONSE;
        }


        projectContract = new ProjectContract();
        projectContract.setBidNumber(bidNumber);
        projectContract.setContractNumber(contractNumber);
        projectContract.setContractName(contractName);
        projectContract.setContractContent(contractContent);
        projectContract.setContractMoney(money);
        projectContract.setContractStatus(ContractStatus.effective.getValue());
        projectContract.setFilePath(FileDirectory.CONTRACT_FILE_DIRECTORY + file.getOriginalFilename());
        projectContract.setIsDeleted(false);
        projectContract.setCreateTime(new Date());
        projectContract.setLastUpdateTime(new Date());
        projectContractService.insert(projectContract);
        projectBidService.updateStatusByBidNumber(bidNumber, BidStatus.contracted.getValue());


        for (ProjectContractItem item : projectContractItems) {
            item.setContractNumber(contractNumber);
            item.setIsDeleted(false);
            item.setCreateTime(new Date());
            item.setLastUpdateTime(new Date());
            projectContractItemService.insert(item);
        }

        return BasePageResult.SUCCESS_RESPONSE;

    }

    public BasePageResult<ProjectContract> queryProjectContracts(Integer contractStatus) {

        BasePageResult<ProjectContract> response = BasePageResult.getSuccessResponse();
        response.setData(projectContractService.findByStatus(contractStatus));
        return response;
    }

    public PageResult<ContractViewBean> getContractListPageResult(PageParam pageParam) {
        PageResult<ContractViewBean> pageResult = new PageResult<>();
        List<ProjectContract> contracts = projectContractService.findByPageParam(pageParam);
        pageResult.setTotal(projectContractService.getTotal());
        pageResult.setRows(toContractViewBean(contracts));
        return pageResult;
    }

    private List<ContractViewBean> toContractViewBean(List<ProjectContract> contracts) {
        List<ContractViewBean> list = new ArrayList<>(contracts.size());
        Map<String, ProjectBidding> biddingMap = new HashMap<>();
        Map<String, Project> projectMap = new HashMap<>();
        for (ProjectContract contract : contracts) {
            ProjectBid bid = projectBidService.findByBidNumber(contract.getBidNumber());
            if (bid == null) {
                continue;
            }
            ProjectBidding projectBidding = biddingMap.get(bid.getBiddingNumber());
            if (projectBidding == null) {
                projectBidding = projectBiddingService.findByBiddingNumber(bid.getBiddingNumber());
                if (projectBidding == null) {
                    continue;
                }
                biddingMap.put(bid.getBiddingNumber(), projectBidding);
            }

            Project project = projectMap.get(projectBidding.getProjectNumber());
            if (project == null) {
                project = projectService.findByProjectNumber(projectBidding.getProjectNumber());
                if (project == null) {
                    continue;
                }
                projectMap.put(projectBidding.getProjectNumber(), project);
            }
            ContractViewBean contractViewBean = new ContractViewBean();
            contractViewBean.setBidNumber(bid.getBidNumber());
            contractViewBean.setBidCompany(bid.getBidCompany());
            contractViewBean.setProjectName(project.getProjectName());
            contractViewBean.setProjectNumber(project.getProjectNumber());
            contractViewBean.setContractNumber(contract.getContractNumber());
            contractViewBean.setContractName(contract.getContractName());
            contractViewBean.setContractMoney(contract.getContractMoney());
            contractViewBean.setContractStatus(contract.getContractStatus());
            contractViewBean.setFinishDay(contract.getFinishDay());
            contractViewBean.setFilePath(contract.getFilePath());
            contractViewBean.setFileName(FileDirectory.getFileName(contract.getFilePath()));
            list.add(contractViewBean);
        }
        return list;
    }


    public ContractViewBean findContractViewBean(String contractNumber) {

        ProjectContract contract = projectContractService.findByContractNumber(contractNumber);
        if (contract == null) {
            return null;
        }
        ContractViewBean contractViewBean = new ContractViewBean();
        contractViewBean.setContractNumber(contract.getContractNumber());
        contractViewBean.setContractName(contract.getContractName());
        contractViewBean.setBidNumber(contract.getBidNumber());
        contractViewBean.setContractContent(contract.getContractContent());
        contractViewBean.setContractStatus(contract.getContractStatus());
        contractViewBean.setFinishDay(contract.getFinishDay());
        contractViewBean.setFilePath(contract.getFilePath());
        contractViewBean.setFileName(FileDirectory.getFileName(contract.getFilePath()));
        contractViewBean.setContractMoney(contract.getContractMoney());
        List<ProjectContractItem> items = projectContractItemService.findByContractNumber(contractNumber);
        List<ContractItemViewbean> itemViewbeans = new ArrayList<>(items.size());
        for (ProjectContractItem item : items) {
            ContractItemViewbean itemViewbean = new ContractItemViewbean();
            itemViewbean.setItemId(item.getId());
            itemViewbean.setItemContent(item.getItemContent());
            itemViewbean.setItemMoney(item.getItemMoney());
            if (item.getFinishDay() != null) {
                itemViewbean.setFinishDay(DateUtil.formatToYMD(item.getFinishDay()));
            }
            itemViewbeans.add(itemViewbean);
        }
        contractViewBean.setItems(itemViewbeans);
        return contractViewBean;

    }

    @Transactional(value = DataSourceInternalControlConfig.TX_MANAGER)
    public BasePageResult sureFinishItem(Integer itemId) {
        ProjectContractItem item = projectContractItemService.findById(itemId);
        if (item == null) {
            return BasePageResult.ERROR_RESPONSE;
        }
        projectContractItemService.updateFinishDay(itemId, new Date());
        Integer count = projectContractItemService.findUnfinishCount(item.getContractNumber());
        if (count == 0) {
            projectContractService.updateStatusAndFinishDay(item.getContractNumber(), ContractStatus.finish.getValue(), new Date());
        }
        return BasePageResult.SUCCESS_RESPONSE;
    }


    public BasePageResult<ContractViewBean> getContractContent(String contractNumber) {
        ProjectContract contract = projectContractService.findByContractNumber(contractNumber);
        if (contract == null) {
            return BasePageResult.CONTRACT_NO_EXIST_RESPONSE;
        }

        BasePageResult<ContractViewBean> successResponse = BasePageResult.getSuccessResponse();
        ContractViewBean contractViewBean = new ContractViewBean();
        contractViewBean.setContractContent(contract.getContractContent());
        successResponse.setData(Arrays.asList(contractViewBean));
        return successResponse;
    }

    public ContractViewBean findContractViewBeanByBidNumber(String bidNumber) {
        ProjectContract contract = projectContractService.findbyBidNumber(bidNumber);
        if (contract == null) {
            return null;
        }
        return findContractViewBean(contract.getContractNumber());
    }


    @Autowired
    public void setProjectContractService(ProjectContractService projectContractService) {
        this.projectContractService = projectContractService;
    }

    @Autowired
    public void setProjectContractItemService(ProjectContractItemService projectContractItemService) {
        this.projectContractItemService = projectContractItemService;
    }

    @Autowired
    public void setProjectBidService(ProjectBidService projectBidService) {
        this.projectBidService = projectBidService;
    }

    @Autowired
    public void setProjectBiddingService(ProjectBiddingService projectBiddingService) {
        this.projectBiddingService = projectBiddingService;
    }

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Autowired
    public void setFilePathPrefix(String filePathPrefix) {
        this.filePathPrefix = filePathPrefix;
    }


}

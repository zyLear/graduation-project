package com.zylear.internalcontrol.admin.bean;

import java.util.Date;
import java.util.List;

/**
 * Created by xiezongyu on 2018/4/21.
 */
public class ContractViewBean {

    private String projectNumber;
    private String projectName;
    private String bidNumber;
    private String bidCompany;
    private String contractNumber;
    private String contractName;
    private Double contractMoney;
    private Date finishDay;
    private Integer contractStatus;
    private String filePath;
    private String contractContent;

    private List<ContractItemViewbean> items;

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

    public String getBidNumber() {
        return bidNumber;
    }

    public void setBidNumber(String bidNumber) {
        this.bidNumber = bidNumber;
    }

    public String getBidCompany() {
        return bidCompany;
    }

    public void setBidCompany(String bidCompany) {
        this.bidCompany = bidCompany;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public Double getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(Double contractMoney) {
        this.contractMoney = contractMoney;
    }

    public Date getFinishDay() {
        return finishDay;
    }

    public void setFinishDay(Date finishDay) {
        this.finishDay = finishDay;
    }

    public Integer getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(Integer contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getContractContent() {
        return contractContent;
    }

    public void setContractContent(String contractContent) {
        this.contractContent = contractContent;
    }

    public List<ContractItemViewbean> getItems() {
        return items;
    }

    public void setItems(List<ContractItemViewbean> items) {
        this.items = items;
    }
}

package com.zylear.internalcontrol.admin.domain;

import java.util.Date;

public class ProjectBidding {
    private Integer id;

    private String biddingNumber;

    private String projectNumber;

    private String biddingName;

    private Double prices;

    private Byte biddingStatus;

    private String filePath;

    private Byte isDeleted;

    private Date createTime;

    private Date lastUpdateTime;

    private String biddingContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBiddingNumber() {
        return biddingNumber;
    }

    public void setBiddingNumber(String biddingNumber) {
        this.biddingNumber = biddingNumber;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getBiddingName() {
        return biddingName;
    }

    public void setBiddingName(String biddingName) {
        this.biddingName = biddingName;
    }

    public Double getPrices() {
        return prices;
    }

    public void setPrices(Double prices) {
        this.prices = prices;
    }

    public Byte getBiddingStatus() {
        return biddingStatus;
    }

    public void setBiddingStatus(Byte biddingStatus) {
        this.biddingStatus = biddingStatus;
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

    public String getBiddingContent() {
        return biddingContent;
    }

    public void setBiddingContent(String biddingContent) {
        this.biddingContent = biddingContent;
    }
}
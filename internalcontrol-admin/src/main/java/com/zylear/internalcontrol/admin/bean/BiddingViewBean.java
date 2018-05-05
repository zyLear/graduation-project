package com.zylear.internalcontrol.admin.bean;

import java.util.Date;

/**
 * Created by xiezongyu on 2018/4/15.
 */
public class BiddingViewBean {

    private Integer id;
    private String projectNumber;
    private String projectName;
    private String biddingNumber;
    private String biddingName;
    private Integer biddingStatus;
    private Date biddingStartTime;
    private Date biddingEndTime;
    private String filePath;
    private Double prices;

    private String biddingContent;

    private String fileName;

    private Integer biddedCount;

    private Boolean bidded;


    public Integer getBiddedCount() {
        return biddedCount;
    }

    public void setBiddedCount(Integer biddedCount) {
        this.biddedCount = biddedCount;
    }

    public Boolean getBidded() {
        return bidded;
    }

    public void setBidded(Boolean bidded) {
        this.bidded = bidded;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBiddingContent() {
        return biddingContent;
    }

    public void setBiddingContent(String biddingContent) {
        this.biddingContent = biddingContent;
    }

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

    public String getBiddingNumber() {
        return biddingNumber;
    }

    public void setBiddingNumber(String biddingNumber) {
        this.biddingNumber = biddingNumber;
    }

    public String getBiddingName() {
        return biddingName;
    }

    public void setBiddingName(String biddingName) {
        this.biddingName = biddingName;
    }

    public Integer getBiddingStatus() {
        return biddingStatus;
    }

    public void setBiddingStatus(Integer biddingStatus) {
        this.biddingStatus = biddingStatus;
    }

    public Date getBiddingStartTime() {
        return biddingStartTime;
    }

    public void setBiddingStartTime(Date biddingStartTime) {
        this.biddingStartTime = biddingStartTime;
    }

    public Date getBiddingEndTime() {
        return biddingEndTime;
    }

    public void setBiddingEndTime(Date biddingEndTime) {
        this.biddingEndTime = biddingEndTime;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Double getPrices() {
        return prices;
    }

    public void setPrices(Double prices) {
        this.prices = prices;
    }
}

package com.zylear.internalcontrol.admin.bean;

import java.util.Date;

/**
 * Created by xiezongyu on 2018/4/19.
 */
public class BidViewBean {

    private Integer id;
    private String projectNumber;
    private String projectName;
    private String biddingNumber;
    private String biddingName;
    private Integer biddingStatus;
    private String bidNumber;
    private String bidCompany;
    private Double bidPrices;
    private Integer bidStatus;
    private String filePath;

    private String bidContent;

    public String getBidContent() {
        return bidContent;
    }

    public void setBidContent(String bidContent) {
        this.bidContent = bidContent;
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

    public Double getBidPrices() {
        return bidPrices;
    }

    public void setBidPrices(Double bidPrices) {
        this.bidPrices = bidPrices;
    }

    public Integer getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(Integer bidStatus) {
        this.bidStatus = bidStatus;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}

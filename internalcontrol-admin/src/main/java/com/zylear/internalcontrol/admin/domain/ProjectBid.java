package com.zylear.internalcontrol.admin.domain;

import java.util.Date;

public class ProjectBid {
    private Integer id;

    private String bidNumber;

    private String biddingNumber;

    private String bidCompany;

    private Double bidPrices;

    private Byte bidStatus;

    private String filePath;

    private Byte isDeleted;

    private Date createTime;

    private Date lastUpdateTime;

    private String bidContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBidNumber() {
        return bidNumber;
    }

    public void setBidNumber(String bidNumber) {
        this.bidNumber = bidNumber;
    }

    public String getBiddingNumber() {
        return biddingNumber;
    }

    public void setBiddingNumber(String biddingNumber) {
        this.biddingNumber = biddingNumber;
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

    public Byte getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(Byte bidStatus) {
        this.bidStatus = bidStatus;
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

    public String getBidContent() {
        return bidContent;
    }

    public void setBidContent(String bidContent) {
        this.bidContent = bidContent;
    }
}
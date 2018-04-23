package com.zylear.internalcontrol.admin.bean;

import java.util.Date;

/**
 * Created by xiezongyu on 2018/4/21.
 */
public class ContractItemViewbean {

    private Integer itemId;
    private String itemContent;
    private Double itemMoney;
    private String finishDay;

    public String getItemContent() {
        return itemContent;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }

    public Double getItemMoney() {
        return itemMoney;
    }

    public void setItemMoney(Double itemMoney) {
        this.itemMoney = itemMoney;
    }

    public String getFinishDay() {
        return finishDay;
    }

    public void setFinishDay(String finishDay) {
        this.finishDay = finishDay;
    }
}

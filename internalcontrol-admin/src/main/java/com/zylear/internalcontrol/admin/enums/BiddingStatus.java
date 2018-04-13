package com.zylear.internalcontrol.admin.enums;

/**
 * Created by xiezongyu on 2018/4/10.
 */
public enum BiddingStatus {
    unknown(-1),
    close(0),
    open(1),
    finish(2);

    private Integer value;

    BiddingStatus(Integer value){
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}

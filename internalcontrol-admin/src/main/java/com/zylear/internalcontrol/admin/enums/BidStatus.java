package com.zylear.internalcontrol.admin.enums;

/**
 * Created by xiezongyu on 2018/4/10.
 */
public enum BidStatus {
    unknown(-1),
    bided(0),
    winning(1),
    unsuccessful(2),
    contracted(3);

    private Integer value;

    BidStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}

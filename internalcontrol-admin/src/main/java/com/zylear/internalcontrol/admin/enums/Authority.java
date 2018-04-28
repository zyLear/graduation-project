package com.zylear.internalcontrol.admin.enums;

/**
 * Created by xiezongyu on 2018/4/28.
 */
public enum Authority {
    unknown(-1),
    bidder(0),
    admin(1);

    private Integer value;

    Authority(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }


}

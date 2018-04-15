package com.zylear.internalcontrol.admin.enums;

/**
 * Created by xiezongyu on 2018/4/9.
 */
public enum ProjectStatus {

    unknown(-1),
    in_approval(0),
    budgeting(1),
    bidding(2),
    pending(3),
    cancel(4);

    private Integer value;

    ProjectStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}

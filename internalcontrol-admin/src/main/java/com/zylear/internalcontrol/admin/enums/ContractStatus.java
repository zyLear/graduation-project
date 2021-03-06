package com.zylear.internalcontrol.admin.enums;

/**
 * Created by xiezongyu on 2018/4/11.
 */
public enum ContractStatus {
    unknown(-1),
//    editing(0),
    effective(0),
    finish(1),
    cancel(2);

    private Integer value;

    ContractStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}

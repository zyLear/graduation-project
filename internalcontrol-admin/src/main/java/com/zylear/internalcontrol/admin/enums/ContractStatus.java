package com.zylear.internalcontrol.admin.enums;

/**
 * Created by xiezongyu on 2018/4/11.
 */
public enum ContractStatus {
    unknown(-1),
//    editing(0),
    effective(1),
    finish(2),
    cancel(3);

    private Integer value;

    ContractStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}

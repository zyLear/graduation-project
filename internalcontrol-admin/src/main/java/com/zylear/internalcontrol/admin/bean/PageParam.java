package com.zylear.internalcontrol.admin.bean;

/**
 * Created by xiezongyu on 04/08/2018.
 */
public class PageParam {
    private Integer pageSize;
    private Integer offSet;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffSet() {
        return offSet;
    }

    public void setOffSet(Integer offSet) {
        this.offSet = offSet;
    }

    public PageParam(Integer pageSize, Integer offSet) {
        this.pageSize = pageSize;
        this.offSet = offSet;
    }

    @Override
    public String toString() {
        return "PageParam{" +
                "pageSize=" + pageSize +
                ", offSet=" + offSet +
                '}';
    }
}

package com.zylear.internalcontrol.admin.bean;

/**
 * Created by xiezongyu on 04/08/2018.
 */
public class PageParam {
    private Integer pageSize;
    private Integer offset;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public PageParam(Integer pageSize, Integer offSet) {
        this.pageSize = pageSize;
        this.offset = offSet;
    }

    @Override
    public String toString() {
        return "PageParam{" +
                "pageSize=" + pageSize +
                ", offset=" + offset +
                '}';
    }
}

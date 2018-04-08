package com.zylear.internalcontrol.admin.bean;

import java.util.List;

/**
 * Created by xiezongyu on 04/08/2018.
 */
public class PageResult<T> {
    private int total;
    private List<T> rows;

    private final static PageResult EMPTY_PAGE = new PageResult();

    public static <T> PageResult<T> emptyPage(){
        return EMPTY_PAGE;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}

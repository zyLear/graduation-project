package com.zylear.internalcontrol.admin.bean;

import java.util.List;

/**
 * Created by xiezongyu on 2018/4/9.
 */
public class BasePageResult<T> {

    private Integer errorCode;
    private String errorMessage;
    private List<T> data;

    public static final BasePageResult SUCCESS_RESPONSE = new BasePageResult(0, "success");
    public static final BasePageResult ERROR_RESPONSE = new BasePageResult(1, "error");

    public static final BasePageResult UPLOAD_FAIL_RESPONSE = new BasePageResult(2, "upload fail");
    public static final BasePageResult FILE_EXIST_RESPONSE = new BasePageResult(3, "file exist");
    public static final BasePageResult ID_EXIST_RESPONSE = new BasePageResult(4, "id exist");

    public static final BasePageResult OVERSPEND_RESPONSE = new BasePageResult(5, "overspend");

    public static final BasePageResult PROJECT_NO_EXIST_RESPONSE = new BasePageResult(6, "project no exist");
    public static final BasePageResult BIDDING_NO_EXIST_RESPONSE = new BasePageResult(7, "bidding no exist");
    public static final BasePageResult BID_NO_EXIST_RESPONSE = new BasePageResult(8, "bid no exist");




    public BasePageResult() {
    }

    public BasePageResult(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public static <T> BasePageResult<T> getSuccessResponse() {
        return new BasePageResult<T>(0, "success");
    }
}

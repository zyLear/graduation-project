package com.zylear.internalcontrol.admin.bean;

/**
 * Created by xiezongyu on 2018/4/9.
 */
public class BasePageResult {

    private Integer errorCode;
    private String errorMessage;

    public static final BasePageResult SUCCESS_RESPONSE = new BasePageResult(0, "success");
    public static final BasePageResult ERROR_RESPONSE = new BasePageResult(1, "error");

    public static final BasePageResult UPLOAD_FAIL_RESPONSE = new BasePageResult(2, "upload fail");
    public static final BasePageResult FILE_EXIST_RESPONSE = new BasePageResult(3, "file exist");
    public static final BasePageResult ID_EXIST_RESPONSE= new BasePageResult(4, "id exist");

    public static final BasePageResult OVERSPEND_RESPONSE= new BasePageResult(5, "overspend");




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
}

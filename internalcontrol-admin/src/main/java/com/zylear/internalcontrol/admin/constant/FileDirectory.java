package com.zylear.internalcontrol.admin.constant;

/**
 * Created by xiezongyu on 2018/4/9.
 */
public class FileDirectory {

    public static final String PROJECT_FILE_DIRECTORY = "project/";
    public static final String BIDDING_FILE_DIRECTORY = "bidding/";
    public static final String CONTRACT_FILE_DIRECTORY = "contract/";
    public static final String BID_FILE_DIRECTORY = "bid/";

    public static final String getFileName(String filePath) {
        return filePath.substring(filePath.lastIndexOf("/") + 1);
    }

}

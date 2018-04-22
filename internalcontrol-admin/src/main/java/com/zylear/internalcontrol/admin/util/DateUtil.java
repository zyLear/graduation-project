package com.zylear.internalcontrol.admin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiezongyu on 2018/4/15.
 */
public class DateUtil {

    private final static SimpleDateFormat YMDHMS = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    private final static SimpleDateFormat YMD = new SimpleDateFormat("YYYY-MM-dd");
    private final static SimpleDateFormat YMD_COMPACT = new SimpleDateFormat("YYYYMMdd");

    public static String formatToYDMHMS(Date date) {
        return YMDHMS.format(date);
    }

    public static String formatToYMDCompact(Date date) {
        return YMD_COMPACT.format(date);
    }

    public static String formatToYMD(Date date) {
        return YMD.format(date);
    }

}

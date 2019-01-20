package com.blog.cloud.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lxw
 * @date 2018/12/31
 * @description
 */
public class DateUtil {

    private static final String normalFMT = "yyyy-MM-dd HH:mm:ss";

    public static Long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static Date normalParseToDate(String text) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(normalFMT);
        try {
            Date date = dateFormat.parse(text);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Long normalParseToDateLong(String text) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(normalFMT);
        try {
            Date date = dateFormat.parse(text);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}

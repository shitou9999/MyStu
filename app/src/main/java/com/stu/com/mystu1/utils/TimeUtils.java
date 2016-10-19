package com.stu.com.mystu1.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间显示工具类
 */
public class TimeUtils {
    /**
     * 返回-------02:30时间格式
     * @param pattern
     * @param milli
     * @return
     */
    public static String formatTime(String pattern, long milli) {
        int m = (int) (milli / (60 * 1000));
        int s = (int) ((milli / 1000) % 60);
        String mm = String.format("%02d", m);
        String ss = String.format("%02d", s);
        return pattern.replace("mm", mm).replace("ss", ss);
    }


    public static final String TAG = "XUtils";
    public static final int INCREASE = 0;
    public static final int DECREASE = 1;

    public static String getTime(long time) {
        long now = System.currentTimeMillis();
        if (now - time < 0) {
            return "来自未来";
        } else {
            float temp = now - time;

            temp /= 1000;
            if (temp < 60) {
                return (int) temp + "秒前";
            }
            temp /= 60;
            if (temp < 60) {
                return (int) temp + "分钟前";
            }
            temp /= 60;
            if (temp < 24) {
                return (int) temp + "小时前";
            }
            temp /= 24;
            if (temp < 30) {
                return (int) temp + "天前";
            }
            if (temp < 365) {
                return (int) temp / 30 + "个月前";
            }
            return "刚刚";
        }
    }

    /**
     * 传递进来时间戳秒，返回天数，大于14天则显示具体发帖时间
     *
     * @param milliSecond：1409900705000
     * @return 10天前
     */
    public static String secondToTime(String milliSecond) {
        if (isBlank(milliSecond))
            return "";
        Date now = new Date();
        BigDecimal db = new BigDecimal(milliSecond);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String data = format.format(db.longValue() * 1000);      //根据时间戳毫秒获取格式为yyyy-MM-dd的时间

        long between = (now.getTime() / 1000 - db.longValue() / 1000 + 8 * 60 * 60);  //8*60*60这个是为了处理时区问题
        long day = between / (24 * 3600);

        long hour = between % (24 * 3600) / 3600;

        long minute = between % 3600 / 60;

        long second = between % 60;

        String result = "";

        if (day > 14) {
            result = data;
        } else if (day <= 14 && day > 0) {
            result = String.valueOf(day) + "天前";
        } else if (hour > 0) {
            result = String.valueOf(hour) + "小时前";
        } else if (minute > 0) {
            result = String.valueOf(minute) + "分钟前";
        } else if (second > 0) {
            result = String.valueOf(second) + "秒前";
        }

        return result;
    }

    public static boolean isBlank(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        }
        return false;
    }




}
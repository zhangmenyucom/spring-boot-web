package com.taylor.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/2/7 2:36
 */
public class StockUtils {
    /**
     * 不需要监控时间
     */
    public static  boolean noNeedMonotorTime() {
        boolean over15_00 = DateCompare(new SimpleDateFormat("HH:MM").format(new Date()), "15:00", "HH:mm") >= 0;
        boolean before9_30 = DateCompare(new SimpleDateFormat("HH:MM").format(new Date()), "9:30", "HH:mm") <= 0;
        boolean duringBreakTime = DateCompare(new SimpleDateFormat("HH:MM").format(new Date()), "11:30", "HH:mm") > 0 && DateCompare(new SimpleDateFormat("HH:MM").format(new Date()), "13:00", "HH:mm") <= 0;
        return over15_00 || before9_30 || duringBreakTime;
    }

    /**
     * 比较时间大小
     **/
    public static int DateCompare(String source, String traget, String type) {
        int ret = 2;
        try {
            SimpleDateFormat format = new SimpleDateFormat(type);
            Date sourcedate = format.parse(source);
            Date tragetdate = format.parse(traget);
            ret = sourcedate.compareTo(tragetdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 危险时间
     * @return
     */
    public static boolean dangerTime() {
        return DateCompare(new SimpleDateFormat("HH:MM").format(new Date()), "14:40", "HH:mm") >= 0;
    }
}

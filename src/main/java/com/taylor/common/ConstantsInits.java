package com.taylor.common;

import java.util.*;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/6 1:47
 */
public class ConstantsInits {

    public static List<String> STOCK_CODE_LIST_SH = new ArrayList<>(0);
    public static List<String> STOCK_CODE_LIST_SZ = new ArrayList<>(0);

    static {
        STOCK_CODE_LIST_SH = Arrays.asList(Constants.STOCK_CODE_SH.split(","));
        STOCK_CODE_LIST_SZ = Arrays.asList(Constants.STOCK_CODE_SZ.split(","));
    }

    /**
     * 监控列表
     **/
    public static List<String> STOCK_ON_MONITOR_LIST = new ArrayList<>();

    public static Map<String, String> STOCK_ON_MONITOR_MAP = new HashMap<>();

    /**
     * 异动监控
     **/
    public static int YIDONG_MONITOR = 1;
}

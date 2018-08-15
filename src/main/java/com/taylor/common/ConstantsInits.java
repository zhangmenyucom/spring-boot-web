package com.taylor.common;

import java.util.*;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/6 1:47
 */
public class ConstantsInits {

    public static List<String> STOCK_CODE_LIST_MAIN = new ArrayList<>(1000);
    public static List<String> STOCK_CODE_LIST_ZHONGXIAO = new ArrayList<>(1000);
    public static List<String> STOCK_CODE_LIST_CHUANGYE = new ArrayList<>(1000);

    static {
        STOCK_CODE_LIST_MAIN = Arrays.asList(Constants.STOCK_CODE_MAIN_BAN.split(","));
        STOCK_CODE_LIST_ZHONGXIAO = Arrays.asList(Constants.STOCK_CODE_ZHONGXIAO_BAN.split(","));
        STOCK_CODE_LIST_CHUANGYE = Arrays.asList(Constants.STOCK_CODE_CHUANGYE_BAN.split(","));
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

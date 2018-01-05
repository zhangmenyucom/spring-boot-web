package com.taylor.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}

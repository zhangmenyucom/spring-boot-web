package com.taylor.common;

import com.taylor.yicai.entity.BillEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/6 1:28
 */
public class Constants {

    public static String GAMEID = "123";
    public static final List<Integer> NUMBER_LIST = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    public static final String BASE_URL = "http://yc3033.com:90";
    public static final String COOKIE = "ASP.NET_SessionId=zqgt0vurufs5okebtm5uq4xq; ValidateToken=dac181eef33aca3b7e7ceec6fcb8afe1; skinStyle=suolaier; CurrentSkin=suolaier; SESSION_COOKIE=1; kangle_runat=1; LoginSessionID=7b605c20ca3bf6fda75a2de2344a70d2";
    public static int initTime = 1;
    /**
     * 失败次数
     **/
    public static int FAIL_TIME = 0;

    /**
     * 允许失败次数
     **/
    public static int FAIL_LIMIT = 6;

    /**
     * 到达失败上限时，重试次数
     **/
    public static int REPEAT_TIME = 0;

    /**
     * 重试失败次数
     **/
    public static int REPEAT_FAILT_TIME = 0;

    /**
     * 重试成功次数
     **/
    public static int REPEAT_SUCCESS_TIME = 0;

    public static BillEnum BILLUNIT = BillEnum.LI;

    public static int FACTOR = 1;
}




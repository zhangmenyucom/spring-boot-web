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

    public static String GAMEID="123";
    public static final List<Integer> NUMBER_LIST = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    public static final String BASE_URL = "http://yc3033.com:90";
    public static  String COOKIE = "SESSION_COOKIE=ASP.NET_SessionId=g30bre21fwzrpraix0qyr2m2; ValidateToken=3ba84ea1dfa82cdccbe60f7e694cf5a3; skinStyle=suolaier; CurrentSkin=suolaier; SESSION_COOKIE=2; kangle_runat=1; LoginSessionID=6b9e0a248e5924bfe5f90be6cb39302c";

    public static int initTime = 1;
    /**
     * 失败次数
     **/
    public static int FAIL_TIME = 0;

    /**
     * 允许失败次数
     **/
    public static int FAIL_LIMIT = 3;

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




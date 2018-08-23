package com.taylor.common;

import com.taylor.yicai.entity.BillEnum;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/6 1:28
 */
public class Constants {
    public static final String BASE_URL = "http://yc3033.com:90";
    public static final String COOKIE = "SESSION_COOKIE=2; kangle_runat=1; ASP.NET_SessionId=jxgkebrnujgnbvyk2tk5x1ht; ValidateToken=22924c5171df5c826d3797609ef46a89; skinStyle=suolaier; CurrentSkin=suolaier; GAMEID=123; XYHandicap=0; multiSelect=false; LoginSessionID=b1af68329b3e440595411d50538bf74d";

    public static int initTime = 1;
    /**
     * 失败次数
     **/
    public static int FAIL_TIME = 0;

    /**
     * 允许失败次数
     **/
    public static int FAIL_LIMIT = 4;

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

    public static BillEnum  BILLUNIT=BillEnum.JIAO;
}




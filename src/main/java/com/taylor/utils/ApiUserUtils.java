package com.taylor.utils;


/**
 * 作者: @author taylor
 * 时间: 2017-08-11 08:58
 * 描述: ApiUserUtils
 */
public class ApiUserUtils {

    /**替换字符串**/
    public static String getCode(String APPID, String REDIRECT_URI, String SCOPE) {
        return String.format(ResourceUtil.getConfigByName("wx.getCode"), APPID, REDIRECT_URI, SCOPE);
    }

    /**替换字符串**/
    public static String getWebAccess(String code,String appId,String secret) {
        return String.format(ResourceUtil.getConfigByName("wx.webAccessTokenhttps"), appId,secret,code);
    }

    /**替换字符串**/
    public static String getUserMessage(String access_token, String openid) {
        return String.format(ResourceUtil.getConfigByName("wx.userMessage"), access_token, openid);
    }
}
package com.taylor.entity.stock;

import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 19:13
 */
@Data
public class BaseQueryBean {
    /**
     * 请求来源
     **/
    private String from;
    /**
     * 请求系统版本
     **/
    private String os_ver;

    /**
     * 操作类型
     **/
    private String cuid;

    private String vv;
    /**
     * 请求返回数据格式
     **/
    private String format;

    /**
     * 股票代码
     **/
    private String stockCode;

    private String timestamp;
}

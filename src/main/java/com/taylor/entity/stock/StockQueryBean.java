package com.taylor.entity.stock;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/5 16:26
 */
@Data
public class StockQueryBean implements Serializable {
    private static final long serialVersionUID = 3930601888186903572L;
    /**
     * 请求来源
     **/
    private String from;

    /**
     * 请求系统版本
     **/
    private String os_ver;
    /****/
    private String cuid;

    private String vv;
    /**
     * 请求返回数据格式
     **/
    private String format;

    /**
     * 股票代码
     **/
    private String stock_code;

    private String step;

    private String start;

    private String count;

    private String fq_type;

    private String timestamp;
}

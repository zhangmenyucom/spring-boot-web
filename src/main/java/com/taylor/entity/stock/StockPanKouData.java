package com.taylor.entity.stock;

import com.taylor.common.CommonRequest;
import com.taylor.common.JsonUtil;
import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/23 21:31
 */
@Data
public class StockPanKouData {
    /**
     * 股票名称
     **/
    private String stockName;

    /**
     * 股票代码
     **/
    private String stockCode;
    /**
     * 当前价
     **/
    private Double currentPrice;
    /**
     * 昨收价
     **/
    private Double yesPrice;
    /**
     * 开盘价
     **/
    private Double openPrice;
    /**
     * 交易量（手）
     **/
    private Double exchangeTotal;

    /**
     * 外盘
     **/
    private Double outer;

    /**
     * 内盘
     **/
    private Double inner;

    /**
     * 买1价
     **/
    private Double b1Price;
    /**
     * 买1数
     **/
    private Double b1Number;
    /**
     * 买2价
     **/
    private Double b2Price;
    /**
     * 买2数
     **/
    private Double b2Number;
    /**
     * 买3价
     **/
    private Double b3Price;
    /**
     * 买3数
     **/
    private Double b3Number;
    /**
     * 买4价
     **/
    private Double b4Price;
    /**
     * 买4数
     **/
    private Double b4Number;
    /**
     * 买5价
     **/
    private Double b5Price;
    /**
     * 买5数
     **/
    private Double b5Number;

    /**
     * 卖1价
     **/
    private Double s1Price;
    /**
     * 卖1数
     **/
    private Double s1Number;
    /**
     * 卖2价
     **/
    private Double s2Price;
    /**
     * 卖2数
     **/
    private Double s2Number;
    /**
     * 卖3价
     **/
    private Double s3Price;
    /**
     * 卖3数
     **/
    private Double s3Number;
    /**
     * 卖4价
     **/
    private Double s4Price;
    /**
     * 卖4数
     **/
    private Double s4Number;
    /**
     * 卖5价
     **/
    private Double s5Price;
    /**
     * 卖5数
     **/
    private Double s5Number;

    /**
     * 涨跌
     **/
    private Double upDownMount;
    /**
     * 涨跌百分比
     **/
    private Double upDownMountPercent;

    /**
     * 最高价
     **/
    private Double maxPrice;

    /**
     * 最低价
     **/
    private Double miniPrice;

    /**
     * 成交额
     **/
    private Double exchangeValue;


    /**
     * 换手率
     **/
    private Double exchangeRatio;

    /**
     * 市盈率动
     **/
    private Double marketEarnActive;

    /**
     * 市盈率静
     **/
    private Double marketEarnStatic;

    /**
     * 振幅
     **/
    private String amplitude;

    /**
     * 流通市值
     **/
    private Double marketValue;

    /**
     * 总市值
     **/
    private Double totalValue;

    /**
     * 涨停价
     **/
    private Double topPrice;

    /**
     * 跌停价
     **/
    private Double bowtomPrice;

    /**
     * 量比
     **/
    private Double liangBi;

    public static void main(String... args) {
        StockPanKouData sh600116 = CommonRequest.getStockPanKouData("sh600116");
        System.out.println(JsonUtil.transfer2JsonString(sh600116));
    }
}

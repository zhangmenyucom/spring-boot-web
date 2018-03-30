package com.taylor.entity.stock;

import com.taylor.common.CommonRequest;
import com.taylor.common.JsonUtil;
import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/24 13:57
 */
@Data
public class StockFundInOut {
    /**
     * 0：正常挂牌 -1:停牌
     **/
    private int status;
    /**
     * 代码
     **/
    private String stockCode;
    /**
     * 主力流入
     **/
    private Double mainIn;
    /**
     * 主力流出
     **/
    private Double mainOut;
    /**
     * 主力净流入
     **/
    private double mainTotalIn;
    /**
     * 主力净流入/资金流入流出总和
     **/
    private Double mainInBi;
    /**
     * 散户流入
     **/
    private Double retailIn;
    /**
     * 散户流出
     **/
    private Double retailOut;
    /**
     * 散户净流入
     **/
    private Double retailTotalIn;
    /**
     * 散户净流入/资金流入流出总和
     **/
    private Double retailInBi;
    /**
     * 资金流入流出总和
     **/
    private Double totalIN;
    /**
     * 股票名称
     **/
    private String stockName;
    /**
     * 查询日期
     **/
    private String dateTime;

    public static void main(String... args) {
        System.out.println(JsonUtil.transfer2JsonString(CommonRequest.getStockFundInOutData("SH603188")));
    }
}
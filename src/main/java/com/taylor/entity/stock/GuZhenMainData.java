package com.taylor.entity.stock;

import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/2/27 16:31
 */
@Data
public class GuZhenMainData {
    /**
     * 股票代码
     **/
    private String _code;
    /**
     * 股票名称
     **/
    private String _name;
    /**
     * 股票评分
     **/
    private Float _score;
    /**
     * 2：空头 1：多头
     **/
    private Integer _bull;
    /**
     * 短期建议
     **/
    private String _short;
    /**
     * 中期建议
     **/
    private String _mid;
    /**
     * 长期建议
     **/
    private String _long;
    /**
     * 标题
     **/
    private String _title;
    /**
     * 内容
     **/
    private String _content;
    /**
     * 打败多少股票
     **/
    private Integer _ko;


}

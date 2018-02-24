package com.taylor.stock.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaolu.zhang
 * @desc:股票状态枚举
 * @date: 2018/1/11 17:49
 */
public enum StrategyEnum {
    TYPE1(1, "最近60天kdj黄金交叉数量小于6"),
    TYPE2(2, "kdj差值小于5"),
    TYPE3(3, "kdj差值小于10"),
    TYPE4(4, "kdj差值在5-10之间"),
    TYPE5(5, "kdj日周月上翘"),
    TYPE6(6, "今天kdj差/昨天kdj差>1,今天kdiff大于5,ratio大于0.9"),
    TYPE7(7, "今天kdj差大于5,ratio大于1"),
    TYPE8(8, "日kdj金叉，周kdj上翘，macd不限ratio大于0.9"),
    TYPE9(9, "日kdj金叉，周kdj上翘，量比大于1.1，外盘大于内盘，主力净流入正值,且流入比大于10%，ratio大于0.9"),
    TYPE10(10, "日kdj金叉，周kdj上翘，量比大于1.1，外盘大于内盘，ratio大于0.9"),
    TYPE11(11, "kdj差值小于5,换手率大于0.9"),
    TYPE12(12, "macd在-0.02至+0.02之间"),
    TYPE13(13, "k线程十字架，且macd<=0，kdj>=0,换手率大于1"),
    TYPE14(14, "T型结构，且macd<=0，kdj>=0,换手率大于1"),
    TYPE15(15, "所有策略重合数>=3"),
    TYPE16(16, "天鹅拳形态");


    private Integer code;
    private String desc;

    StrategyEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public static final Map<Integer, String> map = new HashMap<>();

    static {
        for (StrategyEnum enums : StrategyEnum.values()) {
            map.put(enums.code, enums.desc);
        }
    }

    public static String getEnumValue(int code) {
        return map.get(code);
    }
}

package com.taylor.stock.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaolu.zhang
 * @desc:股票状态枚举
 * @date: 2018/1/11 17:49
 */
public enum StockStatusEnum {
    NORMAL(2, "正常"), STOP(0, "停牌");
    private Integer code;
    private String desc;

    StockStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    private static final Map<Integer, String> map = new HashMap<>();

    static {
        for (StockStatusEnum enums : StockStatusEnum.values()) {
            map.put(enums.code, enums.desc);
        }
    }

    public static String getEnumValue(int code) {
        return map.get(code);
    }
}

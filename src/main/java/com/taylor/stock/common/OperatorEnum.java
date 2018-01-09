package com.taylor.stock.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/7 2:56
 */
public enum OperatorEnum {

    BUY(1, "买进"), SELL(-1, "卖出"), WAIT(0, "等待");

    public static final Map<Integer, String> OPERATOR_ENUM_MAP = new HashMap<>(0);

    static {
        OPERATOR_ENUM_MAP.put(BUY.operateCode, BUY.operateDesc);
        OPERATOR_ENUM_MAP.put(SELL.operateCode, SELL.operateDesc);
        OPERATOR_ENUM_MAP.put(WAIT.operateCode, WAIT.operateDesc);
    }

    private int operateCode;

    private String operateDesc;

    OperatorEnum(int operateCode, String operateDesc) {
        this.operateCode = operateCode;
        this.operateDesc = operateDesc;
    }
}

package com.taylor.stock.common;

import com.taylor.stock.strategy.*;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaolu.zhang
 * @desc:股票状态枚举
 * @date: 2018/1/11 17:49
 */
@Getter
public enum StrategyEnum {
    TYPE13(13, "底部十字或T型结构"),
    TYPE14(14, "T型结构，且macd<=0，kdj>=0,换手率大于1"),
    TYPE15(15, "所有策略重合数>=3"),
    TYPE16(16, "天鹅拳形态"),
    TYPE20(20, "近五日缩量阴线，突然放量大阴线"),
    TYPE22(22, "短期内突破压力位"),
    TYPE28(28, "异动股票"),
    TYPE29(29, "龙虎榜"),
    TYPE30(30, "缩量洗盘"),
    TYPE31(31, "介于5与8之间"),
    TYPE32(32, "最近十日有连续两天涨停"),
    TYPE33(33, "交流流入盘"),
    TYPE34(34, "对倒洗盘"),
    TYPE35(35, "V型或倒V型底");

    public static final Map<Integer, IStrategy> STRATEGY_MAP = new HashMap<>();

    static {
        STRATEGY_MAP.put(16, new TianEQuanStrategy());
        STRATEGY_MAP.put(13, new ShiZiStrategy());
        STRATEGY_MAP.put(20, new BigYinLineStrategy());
        STRATEGY_MAP.put(29, new LongHuBang());
        STRATEGY_MAP.put(30, new SuoLiangXipanStrategy());
        STRATEGY_MAP.put(31, new Between5and8());
        STRATEGY_MAP.put(32, new TwoDaysTopStrategy());
        STRATEGY_MAP.put(33, new FoundInStrategy());
        STRATEGY_MAP.put(34, new DuiDaoXiPanStrategy());
        STRATEGY_MAP.put(35, new VXinDiStrategy());
    }

    private Integer code;

    private String desc;

    StrategyEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
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

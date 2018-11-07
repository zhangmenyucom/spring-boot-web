package com.taylor.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/11/7 23:01
 */

@Getter
public enum BigPanDataEnum {
    SHANG_ZHI("s_sh000001", "上指"),
    SHEN_ZHI("s_sz399001", "深指"),
    CHUANG_ZHI("s_sz399006", "创指"),
    HENG_ZHI("r_hkHSI", "恒指"),
    NA_ZHI("usIXIC", "纳指"),
    DAO_ZHI("usDJI", "道指"),
    BIAO_PU("usINX", "标普");
    private String code;
    private String desc;

    BigPanDataEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static final Map<String, BigPanDataEnum> BIGPAN_MAP = new HashMap<>();

    static {
        Arrays.asList(BigPanDataEnum.values()).forEach(e -> BIGPAN_MAP.put(e.getCode(), e));
    }
}

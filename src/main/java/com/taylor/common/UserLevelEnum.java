package com.taylor.common;

import lombok.Getter;

import java.util.*;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/8/28 22:23
 */
@Getter
public enum UserLevelEnum {
    /**
     * 普通会员
     **/
    NORMAL(1, "普通会员"),
    /**
     * VIP
     **/
    VIP(2, "VIP"),
    /**
     * 平台
     **/
    PLAT_FORM(3, "平台");

    UserLevelEnum(Integer levelId, String levelName) {
        this.levelId = levelId;
        this.levelName = levelName;
    }

    private Integer levelId;

    private String levelName;

    public static final Map<Integer, UserLevelEnum> LEVEL_MAP = new HashMap<>();

    public static final Map<Integer, List<String>> USER_LEVEL_PERMISSION_LIST_MAP = new HashMap<>();

    static {
        Arrays.asList(UserLevelEnum.values()).forEach(e -> LEVEL_MAP.put(e.getLevelId(), e));

        USER_LEVEL_PERMISSION_LIST_MAP.put(NORMAL.getLevelId(), new ArrayList<>());
        USER_LEVEL_PERMISSION_LIST_MAP.put(VIP.getLevelId(), new ArrayList<>(Arrays.asList("lottery:add", "lottery:delete", "lottery:update")));
        USER_LEVEL_PERMISSION_LIST_MAP.put(PLAT_FORM.getLevelId(), new ArrayList<>(Arrays.asList("lottery:list", "lottery:add", "lottery:delete", "lottery:update")));
    }
}

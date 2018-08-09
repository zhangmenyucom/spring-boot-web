package com.taylor.yicai.entity;/**
 * ${author} on 2018/8/9.
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Add some description about this class.
 *
 * @author zhangxiaolu
 * @since 2018/8/9 9:32
 */
@AllArgsConstructor
@Getter
public enum BetStrategyEnum {
    D_S("单|双", 1),
    S_D("双|单", 1),
    DS_S("单,双|双", 2),
    DS_D("单,双|单", 2),
    D_DS("单|单,双", 2),
    S_DS("双|单,双", 2);


    private String content;

    private int n;
}

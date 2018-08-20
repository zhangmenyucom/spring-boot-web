package com.taylor.yicai.entity;/**
 * ${author} on 2018/8/9.
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * Add some description about this class.
 *
 * @author zhangxiaolu
 * @since 2018/8/9 9:24
 */
@AllArgsConstructor
@Getter
public enum BillEnum {
    YUAN(1,BigDecimal.valueOf(1.0)),JIAO(2,BigDecimal.valueOf(0.1)),FENG(3,BigDecimal.valueOf(0.01)),LI(4,BigDecimal.valueOf(0.001));

    private int unit;

    private BigDecimal value;
}
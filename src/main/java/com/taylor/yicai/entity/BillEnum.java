package com.taylor.yicai.entity;/**
 * ${author} on 2018/8/9.
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Add some description about this class.
 *
 * @author zhangxiaolu
 * @since 2018/8/9 9:24
 */
@AllArgsConstructor
@Getter
public enum BillEnum {
    YUAN(1,1.0f),JIAO(2,0.1f),FENG(3,0.01f),LI(4,0.001f);

    private int unit;

    private float value;
}

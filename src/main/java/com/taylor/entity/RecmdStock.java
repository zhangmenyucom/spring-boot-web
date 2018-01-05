package com.taylor.entity;

import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/6 2:53
 */
@Data
public class RecmdStock {
    private Long id;

    private String stockCode;

    private double macd;
}

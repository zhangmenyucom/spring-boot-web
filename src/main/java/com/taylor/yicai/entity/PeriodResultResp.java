package com.taylor.yicai.entity;

import lombok.Data;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/8/9 1:32
 */
@Data
public class PeriodResultResp {
    private int total;
    private List<PeriodResult> list;
}

package com.taylor.entity;

import lombok.Data;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/12/28 0:45
 */
@Data
public class FoundInOutEntity {
    /**
     * 流入
     **/
    private Double zlc;
    /**
     * 流出
     **/
    private Double zlr;
    /**
     * 总合
     **/
    private Double je;

    /**
     * 大小单
     **/
    private List<DanInOutEntity> danList;
}

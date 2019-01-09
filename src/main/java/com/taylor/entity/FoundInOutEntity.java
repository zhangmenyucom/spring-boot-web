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

    /**
     * 大单
     **/
    private Double daDan;

    /**
     * 中单
     **/
    private Double zhongDan;

    /**
     * 小单
     **/
    private Double xiaoDan;

    public Double getDaDan() {
        return danList.get(5).getSr() - danList.get(0).getSr();
    }

    public Double getZhongDan() {
        return danList.get(4).getSr() - danList.get(1).getSr();
    }

    public Double getXiaoDan() {
        return danList.get(3).getSr() - danList.get(2).getSr();
    }
}

package com.taylor.entity;

import com.taylor.common.CommonResponse;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 19:22
 */
@Data
public class StockBusinessinfo extends CommonResponse {

    private static final long serialVersionUID = 4413621543793129829L;

    private String date;

    /**
     * 所属行业
     **/
    private String industry;

    /**
     * 主营
     **/
    private String mainBusiness;

    /**
     * 营业收入比
     **/
    private Double majoGrow;

    /**
     * 净利润
     **/
    private Double netIncreaseRate;


}

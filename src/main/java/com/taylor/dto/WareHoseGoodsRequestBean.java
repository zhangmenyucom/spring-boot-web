package com.taylor.dto;

import com.taylor.common.Base;
import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/8/14 14:58
 */
@Data
public class WareHoseGoodsRequestBean extends Base {
    private static final long serialVersionUID = 8940485691559098236L;
    private long merchantId;
    private int pageNumber;
    private int pageSize;
    private String goodsName;
    /**
     * 0:全部 1：菜品 2：套餐
     **/
    private int goodsType;
}

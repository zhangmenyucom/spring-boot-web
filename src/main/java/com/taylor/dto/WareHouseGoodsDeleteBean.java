package com.taylor.dto;

import lombok.Data;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/8/14 15:42
 */
@Data
public class WareHouseGoodsDeleteBean {
    private Long merchantId;
    private List<Long> wareHouseIds;
}

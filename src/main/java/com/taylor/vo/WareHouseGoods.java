package com.taylor.vo;

import com.taylor.common.Base;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class WareHouseGoods implements Serializable {

    private static final long serialVersionUID = -1533731521972262000L;

    private Long wareHouseGoodsId;

    private Long merchantId;

    private String goodsName;

    private String picUrl;

    // 1启用、2停用、3不限
    private Integer status;

    private BigDecimal price;
}

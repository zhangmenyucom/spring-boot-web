package com.taylor.dto;

import com.taylor.common.SearchEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@ToString
public class GoodsSkuUnitQueryBean extends SearchEntity {

    private Long goodsSkuUnitId;
    private Long merchantId;
    private String skuName;
    private String thirdCode;
}
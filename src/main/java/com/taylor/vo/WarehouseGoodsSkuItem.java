package com.taylor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class WarehouseGoodsSkuItem implements Serializable {
	private static final long serialVersionUID = -9034460599274470902L;
	private Long warehouseGoodsSkuItemId;
	private Long goodsSkuGroupId;
	private Long merchantId;
	private Integer goodsOrderType;
	private Long warehouseGoodsId;
	private Long skuUnitId;
	private Integer sequence;
	private Date createTime;
	private Date updateTime;
	private Long version;
    private String skuName;
}
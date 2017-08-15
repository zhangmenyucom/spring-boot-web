package com.taylor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class WarehouseGoodsSkuGroup implements Serializable {
	private Long goodsSkuGroupId;
	private Long merchantId;
	private String skuGroupName;
	private Integer goodsOrderType;
	private Integer sequence;
	private Long warehouseGoodsId;
	private List<WarehouseGoodsSkuItem> goodsSkuGroupItem;
	private Date createTime;
	private Date updateTime;
	private Long version;
}
package com.taylor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class WarehouseGoodsSkuPriceSales implements Serializable {
	private Long skuPriceSalesId;
	private Long merchantId;
	private Integer goodsOrderType;
	private String skuItemsValue;
	private Long warehouseGoodsId;
	private BigDecimal salesPrice;
	private Integer stockCount;
	private Date createTime;
	private Date updateTime;
	private Long version;
    private String thirdId;
}
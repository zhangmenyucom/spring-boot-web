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
public class WarehouseGoodsTastesItem implements Serializable {
	private static final long serialVersionUID = 2604079496629368992L;
	private Long warehouseGoodsTastesItemId;
	private Long goodsTastesGroupId;
	private Long merchantId;
	private Integer goodsOrderType;
	private Long warehouseGoodsId;
	private Long goodsTastesUnitId;
	private Integer sequence;
    private String goodsTastesName;
    private Date createTime;
    private Date updateTime;
    private Long version;
}
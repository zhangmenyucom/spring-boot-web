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
public class WarehouseGoodsTastesGroup implements Serializable {
	private Long goodsTastesGroupId;
	private Long merchantId;
	private String tastesGroupName;
	private Integer goodsOrderType;
	private Integer sequence;
	private Long warehouseGoodsId;
	private List<WarehouseGoodsTastesItem> goodsTastesGroupItem;
    private Date createTime;
    private Date updateTime;
    private Long version;

}
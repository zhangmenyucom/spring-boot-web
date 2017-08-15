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
public class GoodsSkuUnit implements Serializable {
	private static final long serialVersionUID = -4958120811637218138L;
	private Long goodsSkuUnitId;
	private Long merchantId;
	private String skuName;
	private String thirdCode;
	private Integer status;
	private Date createTime;
	private Date updateTime;
	private Long version;
}
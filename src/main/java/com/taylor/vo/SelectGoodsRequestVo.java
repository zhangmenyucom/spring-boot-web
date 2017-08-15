package com.taylor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 
 * 菜品分组排序弹窗页面 
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectGoodsRequestVo {
	private int pageNum = 1;
	private int pageSize = 10;
	private String searchkey = "";
	private Integer orderType = 1; 
	private Integer goodsType = 1;
}

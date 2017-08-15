package com.taylor.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * <p> User: Huiqiang.yang
 * <p> Version: 1.0
 * <p> Date: 2016/9/26 0026
 */

@Data
public class HouseGoodsItemBean {
    private String name;
    private int isTastes;
    private String thirdGoodsId;
    private String singlePrice;
    private String orderGoodsSku;
    private String orderGoodsTastes;
    private String logo;
    private String goodsdtl;
    private Long goodsId;
    private Long merchantId;

    /**
     * 定价类型：1:单一售价/2:启用规格
     */
    private int price;//recmdPrice

    private String reset;
    
    private String picTxtList;

    //用户名称
    private String userName;
    
    private Integer type = 1;//菜品类型

    private String originPrice = "0"; //套餐原价，用于显示

    private String goodsItems; //套餐的菜品列表

	/**
	 * 点餐价格
	 */
	private BigDecimal orderInPrice;
	
	/**
	 * 外卖价格
	 */
	private BigDecimal orderOutPrice;
    
}

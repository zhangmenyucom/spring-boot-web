
/**
*
文件名：HouseGoodsDetail.java
*
* 版本信息：
* 日期：2017-4-1
* Copyright weimob Corporation 2017
* 版权所有
*
*/
	
package com.taylor.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 *
此类描述的是： 返回菜品的详情，包括商品信息
 * @author: youming.tan
 * @version: 2017-4-1 下午5:39:25
 */

@Data
public class HouseGoodsDetail implements Serializable {
	private static final long serialVersionUID = 8574763797663451392L;
	private WareHouseGoods goods = null;
	private List<GoodsExtPicture> picTxtList = null;
	private GoodsSku<WarehouseGoodsSkuGroup, WarehouseGoodsSkuPriceSales> goodsSku = null;
	private Map<String,String> skuNamesMap = null;
	private WarehouseGoodsTastes goodsTastes = null;
	private List<GoodsComboListVo> goodsItems = null;
}

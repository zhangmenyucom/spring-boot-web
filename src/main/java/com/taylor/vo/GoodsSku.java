/**
 * 
 */
package com.taylor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hao.li
 * DTO Root Object
 * GoodsSku<WarehouseGoodsSkuGroup,WarehouseGoodsSkuPriceSales>
 * 
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GoodsSku<T,K> {
	private GoodsSkuByType<T,K> orderInGoodsSku;
	private GoodsSkuByType<T,K> orderOutGoodsSku;
}

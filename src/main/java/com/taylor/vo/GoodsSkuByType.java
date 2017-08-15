package com.taylor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsSkuByType<T,K> {
	private List<T> goodsSkuGroup;
	private List<K> goodsSkuPriceSales;

}

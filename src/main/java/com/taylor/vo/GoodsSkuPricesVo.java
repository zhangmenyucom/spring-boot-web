
/**
*
文件名：GoodsSkuPricesVo.java
*
* 版本信息：
* 日期：2017-4-20
* Copyright weimob Corporation 2017
* 版权所有
*
*/
	
package com.taylor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


/**
 *
此类描述的是：
 * @author: youming.tan
 * @version: 2017-4-20 下午5:38:52
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GoodsSkuPricesVo {
	
	private BigDecimal price;
	
	private List<Long> skuIds;
	
	private List<String> skuNames;

	private Long stockNum = 0L; 
}

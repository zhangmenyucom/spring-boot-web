
/**
*
文件名：SelectedGoodsDto.java
*
* 版本信息：
* 日期：2017-4-11
* Copyright weimob Corporation 2017
* 版权所有
*
*/
	
package com.taylor.dto;

import com.taylor.vo.GoodsSkuPricesVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


/**
 *
此类描述的是：用于前端选择的菜品的信息,用于新增套餐
 * @author: youming.tan
 * @version: 2017-4-11 下午4:02:19
 */

//菜品数据
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedGoodsDto {
	private Long id; //菜品或者套餐id
	private String img;
	private String goodsName;
	private Integer suppOrderType;
    /**
     * 定价类型：1:单一售价/2:启用规格
     */
    private Integer recmdPriceType;
	private BigDecimal price = BigDecimal.ZERO; //最大价格，供参考
	private List<GoodsSkuPricesVo> orderInSkus;
	//菜品的状态
	private Integer status; //SelectedGoodsStatusEnum
}

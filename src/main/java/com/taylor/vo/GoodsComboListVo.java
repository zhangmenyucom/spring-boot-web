
/**
*
文件名：GoodsComboListVo.java
*
* 版本信息：
* 日期：2017-5-2
* Copyright weimob Corporation 2017
* 版权所有
*
*/
	
package com.taylor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 *
此类描述的是：
 * @author: youming.tan
 * @version: 2017-5-2 下午12:45:39
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsComboListVo implements Serializable {
	
	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
		
	private static final long serialVersionUID = 3768720510316200811L;
	private String groupName = "";
	private String goodsName = "";
	private int count = 0;
	private BigDecimal price = BigDecimal.ZERO;
	private int isForce = 0;
}

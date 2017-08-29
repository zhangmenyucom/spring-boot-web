
/**
*
文件名：HouseGoodsBase.java
*
* 版本信息：
* 日期：2017-4-1
* Copyright weimob Corporation 2017
* 版权所有
*
*/
	
package com.taylor.dto;

import com.taylor.vo.GoodsExtPicture;
import com.taylor.vo.WareHouseGoods;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 *
此类描述的是：返回菜品的基本信息
 * @author: youming.tan
 * @version: 2017-4-1 下午10:07:18
 */
@Data
public class HouseGoodsBase implements Serializable {
	
	/**
	*
	serialVersionUID:TODO（用一句话描述这个变量表示什么）
	*
	* @since Ver 1.1
	*/
		
	private static final long serialVersionUID = -1867434322394045999L;
	private WareHouseGoods goods = null;
	private List<GoodsExtPicture> picTxtList = null;
}

package com.taylor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Data
public class GoodsExtPicture implements Serializable {
	private static final long serialVersionUID = 3825140084880737300L;
	private Long goodsPicId;
	private Long merchantId;
	private Long storeId;
	private Long goodsId;
	private String picUrl;
	private String picText;
	private Date createTime;
	private Date updateTime;
	private Long version;
	

	public GoodsExtPicture(Long merchantId, Long storeId, Long goodsId) {
		this.merchantId = merchantId;
		this.storeId = storeId;
		this.goodsId = goodsId;
	}

	public GoodsExtPicture(Long merchantId, Long storeId, Long goodsId, String picUrl, String picText) {
		this.merchantId = merchantId;
		this.storeId = storeId;
		this.goodsId = goodsId;
		this.picUrl = picUrl;
		this.picText = picText;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}
		if(obj instanceof GoodsExtPicture){
			GoodsExtPicture temp = (GoodsExtPicture)obj;
			if(this.picText.equals(temp.getPicText()) && this.picUrl.equals(temp.getPicUrl())){
				return true;
			}
		}
		return false;
	}
	
    
}
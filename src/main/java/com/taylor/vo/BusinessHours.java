package com.taylor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class BusinessHours implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3643142725619087681L;
	private Long businessHoursId;
	private Long merchantId;
	private Long storeId;
	private byte orderType;
	private String businessSegment;
	private Byte week;
	private String startTime;
	private String endTime;
	private Date createTime;
	private Date updateTime;
	private Long version;
	public BusinessHours(Long merchantId, Long storeId, byte orderType,
                         String businessSegment, Byte week, String startTime, String endTime) {
		this.merchantId = merchantId;
		this.storeId = storeId;
		this.orderType = orderType;
		this.businessSegment = businessSegment;
		this.week = week;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	
}
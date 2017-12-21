package com.taylor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author Weilin.Wang update at 2016-12-28
 *
 */
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineOrderExt implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5051287824365233270L;

	private Long extId;

    private Long merchantId;

    private Long storeId;

    private BigDecimal upAmount;

    private Integer deliveryScope;

    private BigDecimal deliveryAmount;

    private BigDecimal overAmount;

    private Byte isFreeDelivery;

    private Byte isTakeout;

    /** 1单一配送费，2多档配送费 **/
    private Integer deliveryType;
    
    /*
     *  外卖菜品模式
     */
    private Integer takeoutGoodsModel;
    
    /*
     * ==null || ==0 表示未开启外卖自提，>0表示开启外卖自提及自提天数限制
     */
    private Integer selfTakeDays;
    
    private Byte isAutoReceive;

    private Byte isDineIn;
    
    /*
     * 堂食菜品模式
     */
    private Integer dinnerGoodsModel;
    
    private Byte dinnerOrSnack;
    
    private Integer businessMode;

    /**0开1关*/
    private Byte isOfferPay;
    
    /*
     * 是否餐到付款,此字段只用于传递数据，不对应online_order_ext表中字段
     */
    @Deprecated
    private Byte isOffPayWay;

    /*
     * 此字段已弃用，无任何实际意义
     */
    private Byte isVipMarketing;
    
    /*
     * == null || ==“”表示未开启预约订座， 否则表示预约电话
     */
    private String appointmentPhone;
    //是否开启预定  1 开启 0 关闭
    private Byte isReserve;

    //是否开启预约配送  1 开启 0 关闭
    private Byte isReserveDeliver;

    private Long version;
    
    /*
     * 此字段备用，无任何实际意义
     */
    private String remark;
    
    /*
     * 此字段已弃用，无任何实际意义,已经迁移到business_hours表
     */
    private String snackStartTime;

    /*
     * 此字段已弃用，无任何实际意义,已经迁移到business_hours表
     */
    private String snackEndTime;
    
    private Date createTime;

    private Date updateTime;

}
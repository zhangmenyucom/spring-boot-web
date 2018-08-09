package com.taylor.yicai.entity;/**
 * ${author} on 2018/8/9.
 */

import lombok.Data;

/**
 * Add some description about this class.
 *
 * @author zhangxiaolu
 * @since 2018/8/9 12:28
 */
@Data
public class MyOrder {
    private String OrderId;
    private String Ordernumber;
    private String PeriodId;
    private String NumberOfPeriod;
    private String GamePlayName;
    private String CreateTime;
    private String Content;
    private String Times;
    private String MoneyModel;
    private String BetNumber;
    private String Amount;
    private String KickBack;
    private String OpenResult;
    private String BettingBalance;
    private int PeriodStatus;
    private int OrderStatus;
    private String IsTrack;
    private String PeriodCloseTime;
    private int OrderResult;
    private String KickbackPercent;
    private String KickbackAmount;
}

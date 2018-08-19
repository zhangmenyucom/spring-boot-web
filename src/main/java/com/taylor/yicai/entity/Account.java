package com.taylor.yicai.entity;

import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/8/19 15:46
 */
@Data
public class Account {
   private String AccountName;
   private String UserName;
   private String GradeName;
   private String Handicap;
   private String CreditBalance;
   private String CreditTitle;
   private boolean IsAgent;
   private boolean IsShowDividend;
   private boolean IsShowDayRate;
   private boolean IsOpenSports;
   private boolean IsShowUserInfo;
   private int Hierarchy;
   private boolean TotalAgentCanEditOdds;
}

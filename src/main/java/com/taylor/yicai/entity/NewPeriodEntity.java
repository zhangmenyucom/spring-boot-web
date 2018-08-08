package com.taylor.yicai.entity;

import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/8/9 2:28
 */
@Data
public class NewPeriodEntity {
    private boolean IsEnabled;
    private boolean fisstopseles;
    private String gameID;
    private String fid;
    private String fnumberofperiod;
    private String fnextperiod;
    private String fstatus;
    private String fsettlefid;
    private String fsettlenumber;
    private String fsettletime;
    private String fnextstarttime;
    private String fclosetime;
    private String fstarttime;
    private String flottostarttime;
    private String ServerTime;
    private String fpreviousperiod;
    private String fpreviousresult;
}

package com.taylor.vo;

import java.io.Serializable;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/12/4 18:33
 */
public class StoreConfigSaveVo implements Serializable{
    private static final long serialVersionUID = -9199346436094089032L;
    private Long merchantId;
    private Long storeId;
    private OnlineOrderExt ext;
    private String notice;
    private String dishes;
    private String timeFrame2;
    /**
     * 员工配置
     */
    private int status;
    private String bizName;
    private Long employeeBizId;
    private String employees;

    /**大小图模式，默认小图模式**/
    private boolean smallPicMode;
}

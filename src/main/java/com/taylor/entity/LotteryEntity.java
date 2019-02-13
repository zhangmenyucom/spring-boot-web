package com.taylor.entity;

import com.taylor.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 lottery
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2019-02-13 18:52:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LotteryEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 活动名称
     **/
    private String name;
    /**
     * 活动标题
     **/
    private String title;
    /**
     * 活动描述
     **/
    private String description;
    /**
     * 活动类型
     **/
    private Integer type;
    /**
     * 活动状态
     **/
    private Integer status;
    /**
     * 发起人id
     **/
    private Long userId;
    /**
     * 参与人数
     **/
    private Integer participantNum;
    /**
     * 所需注数
     **/
    private Integer ticketsNum;
    /**
     * 中奖人ids
     **/
    private String luckyUserIds;
    /**
     * 开始时间
     **/
    private Date startTime;
    /**
     * 开奖或截止时间
     **/
    private Date endTime;
}

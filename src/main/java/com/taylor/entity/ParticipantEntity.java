package com.taylor.entity;

import com.taylor.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 实体
 * 表名 participant
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2019-02-13 18:52:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ParticipantEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 参与人id
     **/
    private Long userId;
    /**
     * 活动id
     **/
    private Long lotteryId;
    /**
     * 下注码
     **/
    private String tickets;
    /**
     * 状态 0：未中奖 1：中奖未添联系方式 2.中奖填联系方式 3.已领奖
     **/
    private Integer status;
    /**
     * 领奖地址
     **/
    private String address;
    /**
     * 联系电话
     **/
    private String phone;
}

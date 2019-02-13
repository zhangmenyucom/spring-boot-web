package com.taylor.entity;

import com.taylor.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 实体
 * 表名 lottery_reward
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2019-02-13 18:52:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LotteryRewardEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 活动id
     **/
    private Long lotteryId;
    /**
     * 奖品名称
     **/
    private String name;
    /**
     * 奖品数量
     **/
    private Integer num;
}

package com.taylor.entity;

import com.taylor.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 实体
 * 表名 reward_items
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2019-02-13 18:52:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RewardItemsEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**  **/
    private Long rewardId;
    /**  **/
    private Integer type;
    /**  **/
    private String url;
    /**  **/
    private String title;
    /**  **/
    private Integer order;
}

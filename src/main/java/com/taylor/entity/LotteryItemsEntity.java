package com.taylor.entity;

import com.taylor.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 实体
 * 表名 lottery_items
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2019-02-13 18:52:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LotteryItemsEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 活动id
     **/
    private Long lotteryId;
    /**
     * 媒体类型 1.文字 2.图片 3.音频 4.视频
     **/
    private Integer type;
    /**
     * 媒体地址
     **/
    private String url;
    /**
     * 标题
     **/
    private String title;
    /**
     * 排序
     **/
    private Integer order;
}

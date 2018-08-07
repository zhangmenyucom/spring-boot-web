package com.taylor.data.common;


import javax.annotation.Nonnull;

/**
 * 重排序策略
 *
 * @author jearton
 * @since 2018/3/21 下午11:03
 */
public interface ResortStrategy {
    Order transfer(@Nonnull Order order);
}

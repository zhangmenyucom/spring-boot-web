package com.taylor.yicai.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/8/9 1:44
 */
@Data
public class BetContent implements Serializable {

    private static final long serialVersionUID = 8418077142761002072L;

    private String gameId;

    private String periodId;

    private boolean isSingle;

    private boolean canAdvance;

    private List<Order> orderList;
}

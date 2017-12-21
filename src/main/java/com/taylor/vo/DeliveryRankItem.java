package com.taylor.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by weilin.wang on 2017/4/1
 * @author taylor
 */
@Data
@NoArgsConstructor
public class DeliveryRankItem implements Serializable {
    private static final long serialVersionUID = -477668364212134712L;
    private Integer rankStart;
    private Integer rankEnd;
    private BigDecimal amount;

    public DeliveryRankItem(int rankStart, int rankEnd, BigDecimal amount) {
        this.rankStart = rankStart;
        this.rankEnd = rankEnd;
        this.amount = amount;
    }
}

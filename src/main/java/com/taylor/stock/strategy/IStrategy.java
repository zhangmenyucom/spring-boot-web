package com.taylor.stock.strategy;

import com.taylor.entity.stock.MashData;
import lombok.Data;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 0:12
 */
@Data
public abstract class IStrategy {
    public String name;

    IStrategy(String name) {
        this.name = name;
    }

   public abstract int doCheck(List<MashData> mashDataList);

}

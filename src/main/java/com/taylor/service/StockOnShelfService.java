package com.taylor.service;

import com.taylor.common.CrudService;
import com.taylor.entity.RecmdStock;
import com.taylor.entity.StockOnShelf;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/6 0:32
 */
public interface StockOnShelfService extends CrudService<StockOnShelf, StockOnShelf> {
    void listen();
}

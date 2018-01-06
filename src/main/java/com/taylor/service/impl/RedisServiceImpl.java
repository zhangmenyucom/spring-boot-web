package com.taylor.service.impl;

import com.taylor.entity.StockData;
import com.taylor.service.RedisService;
import org.springframework.stereotype.Service;


/**
 * @author Administrator
 */
@Service
public class RedisServiceImpl<T> extends RedisService<T> {

    @Override
    protected String getRedisKey() {
        return null;
    }
}
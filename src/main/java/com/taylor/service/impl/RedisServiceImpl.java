package com.taylor.service.impl;

import com.taylor.entity.StockData;
import com.taylor.service.RedisService;
import org.springframework.stereotype.Service;


/**
 * @author Administrator
 */
@Service
public class RedisServiceImpl<T> extends RedisService<T> {
    private static final String BLOCK_REDIS_KEY = "block_data";

    @Override
    protected String getRedisKey() {
        return BLOCK_REDIS_KEY;
    }
}
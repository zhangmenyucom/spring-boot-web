package com.taylor.service;

import com.taylor.service.RedisService;
import org.springframework.stereotype.Component;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/7/22 12:10
 */
@Component
public class RedisServiceImpl extends RedisService {
    private static final String BLOCK_REDIS_KEY = "redis_key";

    @Override
    protected String getRedisKey() {
        return BLOCK_REDIS_KEY;
    }
}
package com.taylor.service.impl;

import com.taylor.service.RedisService;

import java.util.Set;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/7/22 12:10
 */
public class RedisServiceImpl extends RedisService {
    @Override
    protected String getRedisKey() {
        return BLOCK_REDIS_KEY;
    }
}
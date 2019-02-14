package com.taylor.service;


import com.taylor.common.BaseServiceImpl;
import com.taylor.dao.TokenDao;
import com.taylor.entity.TokenEntity;
import com.taylor.utils.CharUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户TokenService接口
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2019-02-14 10:00:31
 */
@Service
public class TokenService extends BaseServiceImpl<TokenEntity, TokenDao> {
    //12小时后过期
    private static final int EXPIRE = 3600 * 12;

    public TokenEntity queryByToken(String token) {
        return this.getDao().queryByToken(token);
    }
    public TokenEntity queryByUserId(Long userId) {
        return getDao().queryByUserId(userId);
    }
    public Map<String, Object> createToken(long userId) {
        //生成一个token
        String token = CharUtil.getRandomString(32);
        //当前时间
        Date now = new Date();

        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        TokenEntity tokenEntity = queryByUserId(userId);
        if (tokenEntity == null) {
            tokenEntity = new TokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            //保存token
            save(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            //更新token
            update(tokenEntity);
        }
        Map<String, Object> map = new HashMap<>(0);
        map.put("token", token);
        map.put("expire", EXPIRE);
        return map;
    }
}

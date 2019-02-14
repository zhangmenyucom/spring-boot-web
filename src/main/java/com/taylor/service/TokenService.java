package com.taylor.service;


import com.taylor.common.BaseServiceImpl;
import com.taylor.dao.TokenDao;
import com.taylor.entity.TokenEntity;
import org.springframework.stereotype.Service;

/**
 * 用户TokenService接口
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2019-02-14 10:00:31
 */
@Service
public class TokenService extends BaseServiceImpl<TokenEntity, TokenDao> {

    public TokenEntity queryByToken(String token) {
        return this.getDao().queryByToken(token);
    }
}

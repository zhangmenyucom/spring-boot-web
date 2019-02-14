package com.taylor.dao;


import com.taylor.common.BaseDao;
import com.taylor.entity.TokenEntity;

/**
 * 用户TokenDao
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2019-02-14 10:00:31
 */
public interface TokenDao extends BaseDao<TokenEntity> {

    TokenEntity queryByToken(String token);
}

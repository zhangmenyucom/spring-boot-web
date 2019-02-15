package com.taylor.dao;


import com.taylor.common.BaseDao;
import com.taylor.entity.TokenEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 用户TokenDao
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2019-02-14 10:00:31
 */
public interface TokenDao extends BaseDao<TokenEntity> {

    TokenEntity queryByToken(@Param("token") String token);

    TokenEntity queryByUserId(@Param("userId") Long userId);
}

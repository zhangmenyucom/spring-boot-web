package com.taylor.dao;

import com.taylor.common.BaseDao;
import com.taylor.entity.UserEntity;
import com.taylor.vo.UserVo;

/**
 * @author Taylor
 */
public interface  UserDao extends BaseDao<UserEntity>{

    UserEntity queryByOpenId(String openId);
}

package com.taylor.service;


import com.taylor.common.BaseServiceImpl;
import com.taylor.dao.UserDao;
import com.taylor.entity.UserEntity;
import com.taylor.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * Service接口
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2019-02-13 18:52:15
 */
@Service
public class UserService extends BaseServiceImpl<UserEntity, UserDao> {

    public UserEntity queryByOpenId(@Param("openId")String openId) {
        return this.getDao().queryByOpenId(openId);
    }
}

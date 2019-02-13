package com.taylor.entity;

import com.taylor.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体
 * 表名 user
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2019-02-13 18:52:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**  **/
    private Integer gender;
    /**  **/
    private Date birthday;
    /**
     * 用户级别
     **/
    private Integer userLevelId;
    /**  **/
    private String nickname;
    /**  **/
    private String mobile;
    /**  **/
    private String avatar;
    /**
     * 积分
     **/
    private Long point;
    /**  **/
    private String weixinOpenid;
    /**  **/
    private Date registerTime;
    /**  **/
    private Date lastLoginTime;
}

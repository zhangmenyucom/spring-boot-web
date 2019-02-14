package com.taylor.vo;

import com.taylor.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


/**
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-08-15 08:03:41
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserVo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**主键**/
    private Long userId;
    /**性别**/
    private Integer gender;
    /**出生日期**/
    private Date birthday;
    /**注册时间**/
    private Date registerTime;
    /**最后登录时间**/
    private Date lastLoginTime;
    /**最后登录Ip**/
    private String lastLoginIp;
    /**会员等级**/
    private Long userLevelId;
    /**会员等级名称**/
    private String userLevel;
    /**别名**/
    private String nickname;
    /**手机号码**/
    private String mobile;
    /**注册Ip**/
    private String registerIp;
    /**头像**/
    private String avatar;
    /**微信Id**/
    private String weixinOpenid;
    /**积分**/
    private Long point;
}

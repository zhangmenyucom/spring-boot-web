package com.taylor.entity;

import com.taylor.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户Token实体
 * 表名 token
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2019-02-14 10:00:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TokenEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**  **/
    private Long userId;
    /**
     * token
     **/
    private String token;
    /**
     * 过期时间
     **/
    private Date expireTime;
}

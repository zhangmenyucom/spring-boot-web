package com.taylor.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2016年10月27日 下午10:11:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApiException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String errmsg;
    private int errno = 500;

    public ApiException(String errmsg) {
        super(errmsg);
        this.errmsg = errmsg;
    }

    public ApiException(String errmsg, Throwable e) {
        super(errmsg, e);
        this.errmsg = errmsg;
    }

    public ApiException(String errmsg, int errno) {
        super(errmsg);
        this.errmsg = errmsg;
        this.errno = errno;
    }

    public ApiException(String errmsg, int errno, Throwable e) {
        super(errmsg, e);
        this.errmsg = errmsg;
        this.errno = errno;
    }
}

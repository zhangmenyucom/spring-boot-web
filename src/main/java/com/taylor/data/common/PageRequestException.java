package com.taylor.data.common;

/**
 * Add some description about this class.
 *
 * @author dong.zhou
 * @since 2018/4/12 下午6:06
 */
public class PageRequestException extends RuntimeException {
    public PageRequestException() {
        super();
    }

    public PageRequestException(String message) {
        super(message);
    }

    public PageRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public PageRequestException(Throwable cause) {
        super(cause);
    }

    protected PageRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

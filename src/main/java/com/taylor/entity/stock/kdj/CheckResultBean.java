package com.taylor.entity.stock.kdj;

import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/2/13 15:54
 */
@Data
public class CheckResultBean {
    private Integer code;
    private String message;
    private Object data;
}

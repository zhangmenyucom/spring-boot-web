package com.taylor.entity.stock;

import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/2/27 11:37
 */
@Data
public class GuZhenResponse {
    private boolean success;
    private String message;
    private GuZhenFirstData data;


}
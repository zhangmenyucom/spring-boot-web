package com.taylor.entity.stock;

import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/2/27 16:31
 */
@Data
public class GuZhenFirstData {
    private String showType;
    private String pid;
    private String title;
    private GuZhenSecondData data;
}

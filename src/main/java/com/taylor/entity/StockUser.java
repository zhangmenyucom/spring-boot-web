package com.taylor.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/2/8 15:15
 */
@Data
public class StockUser implements Serializable {
    private static final long serialVersionUID = 6510791433864516067L;

    private int id;

    private String userName;

    private String password;
}

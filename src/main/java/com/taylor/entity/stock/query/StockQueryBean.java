package com.taylor.entity.stock.query;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/5 16:26
 */
@Data
public class StockQueryBean extends BaseQueryBean implements Serializable {

    private  static final long serialVersionUID = 3930601888186903572L;

    private  String step;

    private String start;

    private String count;

    private String fq_type;

}

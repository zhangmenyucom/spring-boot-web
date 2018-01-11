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

    /**配合start使用 front：在start之前的count个数据，step:间隔** ,back,no**/
    private  String step;

    private String start;

    private String count;

    private String fq_type;

}

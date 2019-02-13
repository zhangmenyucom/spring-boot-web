package com.taylor.common;

import com.taylor.utils.SQLFilter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 查询参数
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-03-14 23:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Query extends LinkedHashMap<String, Object> {

    private static final long serialVersionUID = 1L;
    /**
     * 当前页码
     **/
    private int page;
    /**
     * 每页条数
     **/
    private int limit = 10;

    public Query(Map<String, Object> params, Long merchantId) {
        this.putAll(params);

        //分页参数
        this.page = Integer.parseInt(params.get("page").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
        this.put("offset", (page - 1) * limit);
        this.put("page", page);
        this.put("limit", limit);
        this.put("merchantId", merchantId);

        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        Optional.ofNullable(params.get("sidx")).ifPresent(sidx -> this.put("sidx", SQLFilter.sqlInject(sidx.toString())));
        Optional.ofNullable(params.get("order")).ifPresent(order -> this.put("order", SQLFilter.sqlInject(order.toString())));
    }

    public Query(Map<String, Object> params) {
        this.putAll(params);

        //分页参数
        this.page = Integer.parseInt(params.get("page").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
        this.put("offset", (page - 1) * limit);
        this.put("page", page);
        this.put("limit", limit);

        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        Optional.ofNullable(params.get("sidx")).ifPresent(sidx -> this.put("sidx", SQLFilter.sqlInject(sidx.toString())));
        Optional.ofNullable(params.get("order")).ifPresent(order -> this.put("order", SQLFilter.sqlInject(order.toString())));
    }
}

package com.taylor.entity.stock;

import com.taylor.common.CommonResponse;
import com.taylor.entity.StockBaseInfo;
import lombok.Data;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 19:47
 */
@Data
public class StockBaseInfoResponse extends CommonResponse {
    private static final long serialVersionUID = -1286026648771881006L;
    private List<StockBaseInfo> data;
}

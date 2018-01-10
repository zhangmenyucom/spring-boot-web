package com.taylor.entity.stock;

import com.taylor.common.CommonResponse;
import com.taylor.entity.stock.MashData;
import com.taylor.entity.stock.StockBaseInfo;
import lombok.Data;

import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/9 19:47
 */
@Data
public class StockBaseInfoResponse extends CommonResponse {
    private List<StockBaseInfo> data;
}

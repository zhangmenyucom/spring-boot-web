package com.taylor.stock.request;

import com.taylor.common.CommonRequest;
import com.taylor.common.Constants;
import com.taylor.common.JsonUtil;
import com.taylor.entity.stock.StockBaseInfoResponse;
import com.taylor.entity.stock.StockBaseInfo;
import com.taylor.entity.stock.query.StockBaseQueryBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.methods.GetMethod;

import java.util.List;

/**
 * 获取股票基本数据
 *
 * @author taylor
 */
@Slf4j
public class QueryStockBaseDataRequest {

    public static List<StockBaseInfo> queryStockBaseInfo(StockBaseQueryBean stockQueryBean, GetMethod method) {
        stockQueryBean.setFrom("pc");
        stockQueryBean.setCuid("xxx");
        stockQueryBean.setFormat("json");
        String responseStr = new CommonRequest<StockBaseQueryBean>().executeRequest(stockQueryBean, method);
        StockBaseInfoResponse response = JsonUtil.readJson2Collection(responseStr, StockBaseInfoResponse.class);
        if (response != null && response.getErrorNo() == 0) {
            return response.getData();
        }
        return null;
    }

    public static void main(String... args) {
        GetMethod method = new GetMethod(Constants.METHOD_URL_STOCK_BASE_INFO);
        StockBaseQueryBean stockBaseQueryBean = new StockBaseQueryBean();
        stockBaseQueryBean.setStock_code("SH600004,SH600006".toLowerCase());
        System.out.println(JsonUtil.transfer2JsonString(queryStockBaseInfo(stockBaseQueryBean, method)));
    }
}
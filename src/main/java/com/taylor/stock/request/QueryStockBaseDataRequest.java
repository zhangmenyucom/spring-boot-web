package com.taylor.stock.request;

import com.taylor.common.CommonRequest;
import com.taylor.common.Constants;
import com.taylor.common.JsonUtil;
import com.taylor.entity.StockBaseInfo;
import com.taylor.entity.stock.StockBaseInfoResponse;
import com.taylor.entity.stock.query.StockBaseQueryBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.GetMethod;

import java.util.List;

/**
 * 获取股票基本数据
 *
 * @author taylor
 */
@Slf4j
public class QueryStockBaseDataRequest {

    public static List<StockBaseInfo> queryStockBaseInfo(String stockCodes, HttpMethodBase method) {
        StockBaseQueryBean stockQueryBean=new StockBaseQueryBean();
        stockQueryBean.setStock_code(stockCodes.toLowerCase());
        stockQueryBean.setFrom("pc");
        stockQueryBean.setCuid("xxx");
        stockQueryBean.setFormat("json");
        stockQueryBean.setVv("100");
        stockQueryBean.setOs_ver("1");
        String responseStr = new CommonRequest<StockBaseQueryBean>().executeRequest(stockQueryBean, method);
        StockBaseInfoResponse response = JsonUtil.readJson2Collection(responseStr, StockBaseInfoResponse.class);
        if (response != null && response.getErrorNo() == 0) {
            return response.getData();
        }
        return null;
    }

    public static void main(String... args) {
        GetMethod method = new GetMethod(Constants.METHOD_URL_STOCK_BASE_INFO);
        System.out.println(JsonUtil.transfer2JsonString(queryStockBaseInfo("sh510900", method)));
    }
}
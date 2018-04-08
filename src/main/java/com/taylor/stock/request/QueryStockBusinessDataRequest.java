package com.taylor.stock.request;

import com.taylor.common.CommonRequest;
import com.taylor.common.Constants;
import com.taylor.common.JsonUtil;
import com.taylor.entity.StockBusinessinfo;
import com.taylor.entity.stock.query.StockBaseQueryBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * 获取股票行业数据
 *
 * @author taylor
 */
@Slf4j
public class QueryStockBusinessDataRequest {

    public static StockBusinessinfo queryStockBasicBussinessInfo(String stockCode, HttpMethodBase method) {
        StockBaseQueryBean stockQueryBean = new StockBaseQueryBean();
        stockQueryBean.setStock_code(stockCode.toLowerCase());
        stockQueryBean.setFrom("pc");
        stockQueryBean.setCuid("xxx");
        stockQueryBean.setFormat("json");
        stockQueryBean.setVv("100");
        stockQueryBean.setOs_ver("1");
        String responseStr = new CommonRequest<StockBaseQueryBean>().executeRequest(stockQueryBean, method);
        return JsonUtil.transferToObj(responseStr, StockBusinessinfo.class);
    }

    public static StockBusinessinfo queryStockBasicBussinessInfo(String stockCode) {
        GetMethod method = new GetMethod(Constants.METHOD_URL_STOCK_BUSINESS);
        return queryStockBasicBussinessInfo(stockCode, method);
    }

    public static void main(String... args) {
        System.out.println(queryStockBasicBussinessInfo("sz300482").getIndustry());
    }
}
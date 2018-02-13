package com.taylor.stock.request;

import com.taylor.common.CommonRequest;
import com.taylor.common.Constants;
import com.taylor.common.JsonUtil;
import com.taylor.entity.stock.MashData;
import com.taylor.entity.stock.MashDataResponse;
import com.taylor.entity.stock.query.StockQueryBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.GetMethod;

import java.util.List;

/**
 * 获取股票数据
 *
 * @author taylor
 */
@Slf4j
public class QueryStockMonthDataRequest {

    public static List<MashData> queryLatestResult(String stockCode, HttpMethodBase method) {
        StockQueryBean stockQueryBean = new StockQueryBean();
        stockQueryBean.setFrom("pc");
        stockQueryBean.setCount(2 + "");
        stockQueryBean.setCuid("xxx");
        stockQueryBean.setFormat("json");
        stockQueryBean.setFq_type("no");
        stockQueryBean.setStep(1 + "");
        stockQueryBean.setOs_ver("1");
        stockQueryBean.setVv("100");
        stockQueryBean.setStock_code(stockCode);
        String respStr = null;
        try {
            respStr = new CommonRequest<>().executeRequest(stockQueryBean, method);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("respStr:"+respStr);
        }
        if(respStr==null){
            return null;
        }
        MashDataResponse mashDataResponse = JsonUtil.transferToObj(respStr, MashDataResponse.class);
        if (mashDataResponse.getErrorNo() == 0) {
            return mashDataResponse.getMashData();
        }
        return null;
    }

    public static void main(String... args) {
        GetMethod method = new GetMethod(Constants.METHOD_URL_STOCK_MONTH_INFO);
        System.out.println(queryLatestResult("SZ002186".toLowerCase(), method));

    }
}
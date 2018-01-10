package com.taylor.stock.request;

import com.taylor.common.CommonRequest;
import com.taylor.common.Constants;
import com.taylor.common.JsonUtil;
import com.taylor.entity.stock.TimeStockDataResponse;
import com.taylor.entity.stock.query.TimeStockDataQueryBean;
import org.apache.commons.httpclient.methods.GetMethod;

import java.util.Date;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/10 11:59
 */
public class QueryStockTimeDataRequest {

    public static TimeStockDataResponse queryStockBaseInfo(TimeStockDataQueryBean timeStockDataQueryBean, GetMethod method) {
        timeStockDataQueryBean.setFrom("pc");
        timeStockDataQueryBean.setCuid("xxx");
        timeStockDataQueryBean.setFormat("json");
        timeStockDataQueryBean.setOs_ver("1");
        timeStockDataQueryBean.setVv("100");
        timeStockDataQueryBean.setFrom("pc");
        timeStockDataQueryBean.setTimestamp(new Date().getTime()+"");
        String responseStr = new CommonRequest<TimeStockDataQueryBean>().executeRequest(timeStockDataQueryBean, method);
        return JsonUtil.readJson2Collection(responseStr, TimeStockDataResponse.class);
    }

    public static void main(String... args) {
        GetMethod method = new GetMethod(Constants.METHOD_URL_STOCK_TIME_INFO);
        TimeStockDataQueryBean timeStockDataQueryBean = new TimeStockDataQueryBean();
        timeStockDataQueryBean.setStock_code("SH600004".toLowerCase());
        System.out.println(JsonUtil.transfer2JsonString(queryStockBaseInfo(timeStockDataQueryBean, method)));
    }
}

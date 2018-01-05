package com.taylor.stock.request;

import com.taylor.common.CommonResponse;
import com.taylor.common.JsonUtil;
import com.taylor.entity.stock.StockDailyData;
import com.taylor.entity.stock.StockQueryBean;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * 获取股票数据
 * @author taylor
 */
public class QueryStockDataWithGet {

	public static  CommonResponse<StockDailyData> queryLatestResult(StockQueryBean stockQueryBean) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		//PostMethod method = new PostMethod("https://gupiao.baidu.com/api/stocks/stockdaybar");

		GetMethod method = new GetMethod("https://gupiao.baidu.com/api/stocks/stockdaybar");

		NameValuePair[] data = {
				new NameValuePair("from", "pc"),
				new NameValuePair("os_ver", stockQueryBean.getOs_ver()),
				new NameValuePair("cuid", stockQueryBean.getCuid()),
				new NameValuePair("vv", "100"),
				new NameValuePair("format","json"),
				new NameValuePair("stock_code", stockQueryBean.getStock_code()),
				new NameValuePair("step", stockQueryBean.getStep()),
				new NameValuePair("start", stockQueryBean.getStart()),
				new NameValuePair("count", stockQueryBean.getCount()),
				new NameValuePair("fq_type", stockQueryBean.getFq_type()),
				new NameValuePair("timestamp", Long.valueOf(new Date().getTime()).toString())
		};
		// method使用表单阈值
		method.setQueryString(data);
		// 提交表单
		client.executeMethod(method);
		// 字符流转字节流 循环输出，同get解释
		InputStream inputStream = method.getResponseBodyAsStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		StringBuilder stringBuider = new StringBuilder();
		String str;
		while ((str = br.readLine()) != null) {
			stringBuider.append(str);
		}
		System.out.println(stringBuider.toString());
		CommonResponse resp = JsonUtil.transferToObj(stringBuider.toString(), CommonResponse.class);
		method.releaseConnection();
		return resp;
	}

	public static void main(String... args) throws IOException {
		StockQueryBean stockQueryBean=new StockQueryBean();
		stockQueryBean.setFrom("pc");
		stockQueryBean.setCount(1+"");
		stockQueryBean.setCuid("xxx");
		stockQueryBean.setFormat("json");
		stockQueryBean.setFq_type("no");
		stockQueryBean.setStock_code("sz300377");
		stockQueryBean.setStep(2+"");
		CommonResponse<StockDailyData> stockDailyDataCommonResponse = queryLatestResult(stockQueryBean);
		System.out.println(JsonUtil.transfer2JsonString(stockDailyDataCommonResponse));
	}

}
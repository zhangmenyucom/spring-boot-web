package com.taylor.stock.request;

import com.taylor.common.KLineTypeEnum;
import com.taylor.entity.stock.kdj.CheckResultBean;
import com.taylor.entity.stock.kdj.KdjTimeBean;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author taylor
 */
public class KdjTimeDataRequest {
    private static PostMethod method = new PostMethod("https://gupiao.nicaifu.com/Stock_router.php");

    public static synchronized String postOrder(String stockCode, KLineTypeEnum kLineTypeEnum) {
        StringBuilder stringBuffer = null;
        try {
            HttpClient client = new HttpClient();
            // 表单域的值,既post传入的key=value
            String code = stockCode.substring(2, stockCode.length()) + "." + stockCode.substring(0, 2).toUpperCase();
            NameValuePair[] data = {new NameValuePair("path", "/stock/stock/k_line"), new NameValuePair("data[prod_code]", code), new NameValuePair("data[candle_period]", kLineTypeEnum.getKey() + ""), new NameValuePair("data[candle_mode]", "1"), new NameValuePair("data[data_count]", "2"), new NameValuePair("data[exFieldArr]", "kdj"), new NameValuePair("ts", new Date().getTime() + "")};
            // method使用表单阈值
            method.setRequestBody(data);
            method.setRequestHeader("Referer", "https://gupiao.nicaifu.com");
            method.setContentChunked(true);
            method.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
            // 提交表单
            client.executeMethod(method);
            // 字符流转字节流 循环输出，同get解释
            InputStream inputStream = method.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            stringBuffer = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    public static List<KdjTimeBean> getKdjTimeList(String stockCode, KLineTypeEnum kLineTypeEnum) {
        String result = postOrder(stockCode, kLineTypeEnum);
        String suResult = result.substring(result.indexOf("candle") + 8, result.indexOf("hq_type_code") - 3);
        String finalResult = suResult.substring(suResult.lastIndexOf(":") + 2, suResult.length() - 2).replace("[", "{").replace("]", "}");
        String firstBeanStr = finalResult.substring(finalResult.indexOf("{") + 1, finalResult.lastIndexOf("{") - 2).replace("\"", "");
        String secondBeanStr = finalResult.substring(finalResult.lastIndexOf("{") + 1, finalResult.lastIndexOf("}") - 1).replace("\"", "");
        KdjTimeBean kdjTimeBeanFirst = transterStr2KdjTimeBean(firstBeanStr);
        KdjTimeBean kdjTimeBeanSecond = transterStr2KdjTimeBean(secondBeanStr);
        List<KdjTimeBean> list = new ArrayList<>();
        list.add(kdjTimeBeanFirst);
        list.add(kdjTimeBeanSecond);
        return list;
    }

    private static KdjTimeBean transterStr2KdjTimeBean(String beanStr) {
        String[] beanAttr = beanStr.split(",");
        KdjTimeBean kdjTimeBean = new KdjTimeBean();
        kdjTimeBean.setMin_time(Long.valueOf(beanAttr[0]));
        kdjTimeBean.setOpen_px(Double.valueOf(beanAttr[1]));
        kdjTimeBean.setHigh_px(Double.valueOf(beanAttr[2]));
        kdjTimeBean.setLow_px(Double.valueOf(beanAttr[3]));
        kdjTimeBean.setClose_px(Double.valueOf(beanAttr[4]));
        kdjTimeBean.setBusiness_amount(Double.valueOf(beanAttr[5]));
        kdjTimeBean.setBusiness_balance(Double.valueOf(beanAttr[6]));
        kdjTimeBean.setKdj_k(Double.valueOf(beanAttr[7]));
        kdjTimeBean.setKdj_d(Double.valueOf(beanAttr[8]));
        kdjTimeBean.setKdj_j(Double.valueOf(beanAttr[9]));
        kdjTimeBean.setC_px_change(Double.valueOf(beanAttr[10]));
        kdjTimeBean.setC_px_change_percent(Double.valueOf(beanAttr[11]));
        return kdjTimeBean;
    }

    public static CheckResultBean check(String stockCode, KLineTypeEnum kLineTypeEnum) {
        CheckResultBean checkResultBean=new CheckResultBean();
        List<KdjTimeBean> kdjTimeList = getKdjTimeList(stockCode, kLineTypeEnum);
        KdjTimeBean fisrt = kdjTimeList.get(0);
        KdjTimeBean second = kdjTimeList.get(1);

        if (second.getKdj_k() - second.getKdj_d() >= 0 && fisrt.getKdj_k() - fisrt.getKdj_d() <= 0) {
            checkResultBean.setCode(1);
            return checkResultBean;
        }
        if (second.getKdj_k() - second.getKdj_d() <= 0 && fisrt.getKdj_k() - fisrt.getKdj_d() >= 0) {
            checkResultBean.setCode(-1);
            return checkResultBean;
        }
        if (second.getKdj_k() - second.getKdj_d() >= 0 && second.getKdj_k() - second.getKdj_d() <= 6) {
            checkResultBean.setCode(2);
            return checkResultBean;
        }
        if (second.getKdj_k() - second.getKdj_d() <= 0 && second.getKdj_k() - second.getKdj_d() >=-6) {
            checkResultBean.setCode(3);
            return checkResultBean;
        }
        checkResultBean.setCode(0);
        return checkResultBean;
    }

    public static void main(String... args) {
        System.out.println(postOrder("sh510900", KLineTypeEnum.FIVE_MINI));
    }
}
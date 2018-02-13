package com.taylor.stock.request;

import com.taylor.common.JsonUtil;
import com.taylor.common.KLineTypeEnum;
import com.taylor.entity.stock.kdj.CheckResultBean;
import com.taylor.entity.stock.kdj.MacdTimeBean;
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
public class MacdTimeDataRequest {
    private static PostMethod method = new PostMethod("https://gupiao.nicaifu.com/Stock_router.php");

    public static synchronized String postOrder(String stockCode, KLineTypeEnum kLineTypeEnum) {
        StringBuilder stringBuffer = null;
        try {
            HttpClient client = new HttpClient();
            // 表单域的值,既post传入的key=value
            String code = stockCode.substring(2, stockCode.length()) + "." + stockCode.substring(0, 2).toUpperCase();
            NameValuePair[] data = {new NameValuePair("path", "/stock/stock/k_line"), new NameValuePair("data[prod_code]", code), new NameValuePair("data[candle_period]", kLineTypeEnum.getKey() + ""), new NameValuePair("data[candle_mode]", "1"), new NameValuePair("data[data_count]", "2"), new NameValuePair("data[exFieldArr]", "macd"), new NameValuePair("ts", new Date().getTime() + "")};
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

    public static List<MacdTimeBean> getKdjTimeList(String stockCode, KLineTypeEnum kLineTypeEnum) {
        String result = postOrder(stockCode, kLineTypeEnum);
        String suResult = result.substring(result.indexOf("candle") + 8, result.indexOf("hq_type_code") - 3);
        String finalResult = suResult.substring(suResult.lastIndexOf(":") + 2, suResult.length() - 2).replace("[", "{").replace("]", "}");
        String firstBeanStr = finalResult.substring(finalResult.indexOf("{") + 1, finalResult.lastIndexOf("{") - 2).replace("\"", "");
        String secondBeanStr = finalResult.substring(finalResult.lastIndexOf("{") + 1, finalResult.lastIndexOf("}") - 1).replace("\"", "");
        MacdTimeBean macdTimeBeanFirst = transterStr2KdjTimeBean(firstBeanStr);
        MacdTimeBean macdTimeBeanSecond = transterStr2KdjTimeBean(secondBeanStr);
        List<MacdTimeBean> list = new ArrayList<>();
        list.add(macdTimeBeanFirst);
        list.add(macdTimeBeanSecond);
        return list;
    }

    private static MacdTimeBean transterStr2KdjTimeBean(String beanStr) {
        String[] beanAttr = beanStr.split(",");
        MacdTimeBean macdTimeBean = new MacdTimeBean();
        macdTimeBean.setMin_time(Long.valueOf(beanAttr[0]));
        macdTimeBean.setOpen_px(Double.valueOf(beanAttr[1]));
        macdTimeBean.setHigh_px(Double.valueOf(beanAttr[2]));
        macdTimeBean.setLow_px(Double.valueOf(beanAttr[3]));
        macdTimeBean.setClose_px(Double.valueOf(beanAttr[4]));
        macdTimeBean.setBusiness_amount(Double.valueOf(beanAttr[5]));
        macdTimeBean.setBusiness_balance(Double.valueOf(beanAttr[6]));
        macdTimeBean.setDiff(Double.valueOf(beanAttr[7]));
        macdTimeBean.setDea(Double.valueOf(beanAttr[8]));
        macdTimeBean.setMacd(Double.valueOf(beanAttr[9]));
        macdTimeBean.setC_px_change(Double.valueOf(beanAttr[10]));
        macdTimeBean.setC_px_change_percent(Double.valueOf(beanAttr[11]));
        return macdTimeBean;
    }

    public static CheckResultBean check(String stockCode, KLineTypeEnum kLineTypeEnum) {
        CheckResultBean checkResultBean=new CheckResultBean();
        List<MacdTimeBean> macdTimeList = getKdjTimeList(stockCode, kLineTypeEnum);
        MacdTimeBean fisrt = macdTimeList.get(0);
        MacdTimeBean second = macdTimeList.get(1);
        String result=second.getMacd()-fisrt.getMacd()>0?"macd上翘":"macd下翘";
        if(second.getMacd()-fisrt.getMacd()==0){
            result="水平";
        }
        if (second.getMacd()>=0 && fisrt.getMacd() <= 0) {
            checkResultBean.setCode(1);
            checkResultBean.setMessage(result+",macd为金叉,建议买进");
            return checkResultBean;
        }
        if (second.getMacd()<=0 && fisrt.getMacd() >= 0) {
            checkResultBean.setCode(-1);
            checkResultBean.setMessage(result+",macd为死叉,建议卖出");
            return checkResultBean;
        }
        if (second.getMacd() >= 0 && second.getMacd() <= 1) {
            checkResultBean.setMessage(result+",macd为正,有下跌的趋势");
            checkResultBean.setCode(2);
            return checkResultBean;
        }
        if (second.getMacd() <= 0 && second.getMacd() >=-1) {
            checkResultBean.setMessage(result+",macd为负,有上涨的趋势");
            checkResultBean.setCode(3);
            return checkResultBean;
        }
        checkResultBean.setCode(0);
        checkResultBean.setMessage(result+",macd为"+second.getMacd()+"趋势不明显");
        return checkResultBean;
    }

    public static void main(String... args) {
        System.out.println(postOrder("sh510900",KLineTypeEnum.FIVE_MINI));
        System.out.println(JsonUtil.transfer2JsonString(getKdjTimeList("sh510900",KLineTypeEnum.FIVE_MINI)));
    }
}
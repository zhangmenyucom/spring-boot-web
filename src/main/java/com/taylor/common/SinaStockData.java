package com.taylor.common;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/1/23 19:04
 */
public class SinaStockData {

    public static void main(String... args) {
        InputStreamReader isr = null;
        try {
            String url = "http://hq.sinajs.cn/list=sz002721";
            URL u = new URL(url);
            isr = new InputStreamReader(u.openStream(), "GBK");
            char[] cha = new char[1024];
            int len = isr.read(cha);
            String result = new String(cha, 0, len);
            System.out.println(result);
            result = result.substring(result.indexOf("=")+2,result.indexOf(";")-1);
            String[] stocks = result.split(";");
            for (String stock : stocks) {
                String[] datas = stock.split(",");
                System.out.println(Arrays.toString(datas));
                //根据对照自己对应数据
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

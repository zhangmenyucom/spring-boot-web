package com.taylor.stock.request;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpRequestUtil {
    /**
     * 发起http请求并获取结果
     *
     * @param requestUrl 请求地址
     */
    public static JsonObject getXpath(String requestUrl) {
        JsonObject object = null;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            if (urlCon.getResponseCode() == 200) {
                InputStream is = urlCon.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "utf-8");
                BufferedReader br = new BufferedReader(isr);

                String buffer = br.lines().collect(Collectors.joining());
                br.close();
                isr.close();
                is.close();
                JsonParser parse = new JsonParser();
                object = (JsonObject) parse.parse(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static JsonObject postDownloadJson(String path, String post) {
        try {
            URL url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");// 提交模式
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            printWriter.write(post);//post的参数 xx=xx&yy=yy
            printWriter.flush();
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            JsonReader reader = new JsonReader(new InputStreamReader(bis));//其中jsonContext为String类型的Json数据
            reader.setLenient(true);
            while(reader.hasNext())
            {
                System.out.println(reader.nextString());//开始读对象
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String SendGET(String url,String param){
        String result="";//访问返回结果
        BufferedReader read=null;//读取访问结果

        try {
            //创建url
            URL realurl=new URL(url+"?"+param);
            //打开连接
            URLConnection connection=realurl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //建立连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段，获取到cookies等
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            read = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            String line;//循环读取
            while ((line = read.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(read!=null){//关闭流
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
    //测试
    public static void main(String... args) {
        JsonObject res = postDownloadJson("http://www.iwencai.com/diag/block-detail", "pid=8093&codes=002839&codeType=stock&info=%7B\"view\"%3A%7B\"nolazy\"%3A1%2C\"parseArr\"%3A%7B\"_v\"%3A\"new\"%2C\"dateRange\"%3A%5B%5D%2C\"staying\"%3A%5B%5D%2C\"queryCompare\"%3A%5B%5D%2C\"comparesOfIndex\"%3A%5B%5D%7D%2C\"asyncParams\"%3A%7B\"tid\"%3A137%7D%7D%7D");
        System.out.println(res);
    }
}
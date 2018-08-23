package com.taylor.common;/**
 * ${author} on 2018/8/23.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

/**
 * @author zhangxiaolu
 * @描述
 * @since 2018/8/23 10:45
 */
public class PropertiesUtil {
    public static Map loadProperties(String path) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            Properties pps = new Properties();
            pps.load(new FileInputStream(path));
            Enumeration enum1 = pps.propertyNames();//得到配置文件的名字
            System.out.println(pps.get("COOKIE"));
            return pps;
        }
        System.out.println("配置文件" + path + "不存在,现采用其指定参数或默认参数");
        return null;
    }

    public static void main(String... args) throws IOException {
        loadProperties("c:/config.properties");
    }
}

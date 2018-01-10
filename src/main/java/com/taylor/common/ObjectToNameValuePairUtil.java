package com.taylor.common;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang.ArrayUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author taylor
 */
public class ObjectToNameValuePairUtil<T> {
    /**
     * 将一个类查询方式加入map（属性值为int型时，0时不加入，
     * 属性值为String型或Long时为null和“”不加入）
     * 注：需要转换的必须是对象，即有属性
     */
    public NameValuePair[] transferObj2NameParis(T obj) {
        if (obj == null) {
            return null;
        }
        Class<?> aClass = obj.getClass();
        /**获取类的各个属性值**/
        Field[] fields = getBeanFields(obj.getClass(), obj.getClass().getDeclaredFields());
        List<NameValuePair> nameValuePairsList = new ArrayList<>();

        for (Field field : fields) {
            /**获取类的属性名称**/
            String fieldName = field.getName();
            /**获取类的属性名称对应的值**/
            if (getValueByFieldName(fieldName, obj) != null) {
                NameValuePair nameValuePair = new NameValuePair(fieldName, getValueByFieldName(fieldName, obj).toString());
                nameValuePairsList.add(nameValuePair);
            }
        }
        if (!nameValuePairsList.isEmpty()) {
            return nameValuePairsList.toArray(new NameValuePair[nameValuePairsList.size()]) ;
        }
        return null;
    }

    /**
     * 根据属性名获取该类此属性的值
     *
     * @param fieldName
     * @param object
     */
    private static Object getValueByFieldName(String fieldName, Object object) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = object.getClass().getMethod(getter);
            return method.invoke(object);
        } catch (Exception e) {
            return null;
        }

    }

    private static Field[] getBeanFields(Class cls, Field[] fs) {
        fs = (Field[]) ArrayUtils.addAll(fs, cls.getDeclaredFields());

        if (cls.getSuperclass() != null) {
            Class clsSup = cls.getSuperclass();
            fs = getBeanFields(cls.getSuperclass(), fs);
        }
        return fs;

    }

}
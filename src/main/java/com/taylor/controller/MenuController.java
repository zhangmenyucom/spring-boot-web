package com.taylor.controller;

import com.alibaba.fastjson.JSONObject;
import com.taylor.entity.Menu;
import com.taylor.service.MenuService;
import com.taylor.weixin.AccessTokenThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对订阅号的菜单的操作
 */
@RestController
@RequestMapping("/menu")
@Slf4j
public class MenuController {
    @Autowired
    private MenuService menuService;

    //查询全部菜单
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getMenu() {
        // 调用接口获取access_token
        String at = AccessTokenThread.accessToken.getToken();
        if (at != null) {
            // 调用接口查询菜单
            JSONObject jsonObject = menuService.getMenu(at);
            // 判断菜单创建结果
            return String.valueOf(jsonObject);
        }
        log.info("token为{}", at);
        return "无数据";
    }

    //创建菜单
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public int createMenu() {
        // 调用接口获取access_token
        String at = AccessTokenThread.accessToken.getToken();
        int result = 0;
        if (at != null) {

            // 调用接口创建菜单
            result = menuService.createMenu(getFirstMenu(), at);
            // 判断菜单创建结果
            if (result == 0) {
                log.info("菜单创建成功！");
            } else {
                log.info("菜单创建失败，错误码：{}", result);
            }
        }
        return result;
    }

    //删除菜单
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public int deleteMenu() {
        // 调用接口获取access_token
        String at = AccessTokenThread.accessToken.getToken();
        int result = 0;
        if (at != null) {
            // 删除菜单
            result = menuService.deleteMenu(at);
            // 判断菜单删除结果
            if (result == 0) {
                log.info("菜单删除成功！");
            } else {
                log.info("菜单删除失败，错误码：{}", result);
            }
        }
        return result;
    }


    /**
     * 组装菜单数据
     */
    public static Map<String, Object> getFirstMenu() {
        //第一栏菜单
        Menu menu1 = new Menu();
        menu1.setId("1");
        menu1.setName("第一栏");
        menu1.setType("click");
        menu1.setKey("1");

        Menu menu11 = new Menu();
        menu11.setId("11");
        menu11.setName("第一栏的第一个v3");
        menu11.setType("click");
        menu11.setKey("11");

        Menu menu12 = new Menu();
        menu12.setId("12");
        menu12.setName("第一栏的第二个");
        menu12.setType("click");
        menu12.setKey("12");

        //第二栏
        Menu menu2 = new Menu();
        menu2.setId("2");
        menu2.setName("第二栏");
        menu2.setType("click");
        menu2.setKey("2");

        Menu menu21 = new Menu();
        menu21.setId("21");
        menu21.setName("第二栏的第一个");
        menu21.setType("click");
        menu21.setKey("21");


        Menu menu3 = new Menu();
        menu3.setId("3");
        menu3.setName("第三栏");
        menu3.setType("view");
        menu3.setUrl("http://www.baidu.com");

        //包装第一栏
        Map<String, Object> menuMap11 = new HashMap<>();


        //第一栏第一个
        menuMap11.put("name", menu11.getName());
        menuMap11.put("type", menu11.getType());
        menuMap11.put("key", menu11.getKey());
        List<Map<String, Object>> subMenuMapList1 = new ArrayList<>();
        subMenuMapList1.add(menuMap11);

        //第二栏第二个
        Map<String, Object> menuMap12 = new HashMap<>();
        menuMap12.put("name", menu12.getName());
        menuMap12.put("type", menu12.getType());
        menuMap12.put("key", menu12.getKey());
        subMenuMapList1.add(menuMap12);

        Map<String, Object> menuMap1 = new HashMap<>();
        menuMap1.put("name", menu1.getName());
        menuMap1.put("sub_button", subMenuMapList1);

        //包装第二栏
        Map<String, Object> menuMap21 = new HashMap<>();

        //第二栏第一个
        menuMap21.put("name", menu21.getName());
        menuMap21.put("type", menu21.getType());
        menuMap21.put("key", menu21.getKey());
        List<Map<String, Object>> subMenuMapList2 = new ArrayList<>();
        subMenuMapList2.add(menuMap21);

        Map<String, Object> menuMap2 = new HashMap<>();
        menuMap2.put("name", menu2.getName());
        menuMap2.put("sub_button", subMenuMapList2);

        //包装第三栏
        Map<String, Object> menuMap3 = new HashMap<>();

        menuMap3.put("name", menu3.getName());
        menuMap3.put("type", menu3.getType());
        menuMap3.put("url", menu3.getUrl());

        List<Map<String, Object>> subMenuMapList3 = new ArrayList<>();
        menuMap3.put("sub_button", subMenuMapList3);

        //包装button的List
        List<Map<String, Object>> wechatMenuMapList = new ArrayList<>();
        wechatMenuMapList.add(menuMap1);
        wechatMenuMapList.add(menuMap2);
        wechatMenuMapList.add(menuMap3);

        //最外一层大括号
        Map<String, Object> wechatMenuMap = new HashMap<>();
        wechatMenuMap.put("button", wechatMenuMapList);
        return wechatMenuMap;
    }
}
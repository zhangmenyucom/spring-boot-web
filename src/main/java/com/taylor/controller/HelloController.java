package com.taylor.controller;

import java.util.Map;
 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
/**
 *测试
 * @author Taylor
 * @version v.0.1
 */
@Controller
public class HelloController {
      
       //从 application.properties 中读取配置，如取不到默认值为HelloShanhy
   @Value("${application.hello:Hello Angel}")
    private String hello;
   
      
       @RequestMapping("/helloJsp")
       public StringhelloJsp(Map<String,Object> map){
              System.out.println("HelloController.helloJsp().hello="+hello);
              map.put("hello",hello);
              return"helloJsp";
       }
}
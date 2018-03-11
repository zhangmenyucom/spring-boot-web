package com.taylor.controller;

import com.taylor.common.CommonResponse;
import com.taylor.entity.StockUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * login
 *
 * @author Taylor
 * @version v.0.1
 */
@Controller
@RequestMapping("/")
public class LoginController {
    @RequestMapping("/login")
    @ResponseBody
    public CommonResponse login(@RequestBody StockUser stockUser, HttpServletRequest request, HttpServletResponse response) {
        CommonResponse commonResponse = new CommonResponse();
        if (stockUser != null && "zhangmenyucom".equals(stockUser.getUserName()) && "vbn123VBN".equals(stockUser.getPassword())) {
            request.getSession().setAttribute("user", stockUser);
            commonResponse.setErrorNo(0);
            commonResponse.setErrorMsg("sucess");
        } else {
            commonResponse.setErrorMsg("error");
            commonResponse.setErrorNo(-1);
        }
        return commonResponse;
    }

    @RequestMapping("/logout")
    public String logout(Map<String, Object> map, HttpServletRequest request) {
        request.getSession().setAttribute("user", null);
        return "/login";
    }
}
package com.taylor.component;

import com.taylor.annotation.IgnoreAuth;
import com.taylor.annotation.Permission;
import com.taylor.common.ApiException;
import com.taylor.entity.TokenEntity;
import com.taylor.entity.UserEntity;
import com.taylor.service.TokenService;
import com.taylor.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

import static com.taylor.common.UserLevelEnum.LEVEL_MAP;

/**
 * 权限(Token)验证
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-03-23 15:38
 */
@Slf4j
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    public static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";
    public static final String LOGIN_TOKEN_KEY = "token";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //支持跨域请求
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,X-Nideshop-Token");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

        IgnoreAuth annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class);
        } else {
            return true;
        }

        //如果有@IgnoreAuth注解，则不验证token
        if (annotation != null) {
            return true;
        }

        //从header中获取token
        String token = request.getHeader(LOGIN_TOKEN_KEY);
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(LOGIN_TOKEN_KEY);
        }

        //token为空
        if (StringUtils.isBlank(token)) {
            throw new ApiException("请先登录", 401);
        }

        //查询token信息
        TokenEntity tokenEntity = tokenService.queryByToken(token);
        if (tokenEntity == null) {
            throw new ApiException("请先登录", 401);
        }

        if (handler instanceof HandlerMethod) {
            Permission permissionAnnotation = ((HandlerMethod) handler).getMethodAnnotation(Permission.class);
            UserEntity userEntity = userService.queryObject(tokenEntity.getUserId());
            if (!Arrays.asList(permissionAnnotation.value()).contains(LEVEL_MAP.get(userEntity.getUserLevelId()))) {
                log.info("user{}非法访问接口{}", userEntity.getNickname(), ((HandlerMethod) handler).getMethod().getName());
                throw new ApiException("无权访问", 401);
            }
        }

        //设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(LOGIN_USER_KEY, tokenEntity.getUserId());
        return true;
    }
}

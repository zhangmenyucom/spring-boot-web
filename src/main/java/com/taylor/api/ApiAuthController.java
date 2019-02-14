package com.taylor.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.StringUtils;
import com.taylor.annotation.IgnoreAuth;
import com.taylor.common.R;
import com.taylor.common.UserLevelEnum;
import com.taylor.dao.SysConfigDao;
import com.taylor.entity.SysUserConfigEntity;
import com.taylor.entity.UserEntity;
import com.taylor.service.TokenService;
import com.taylor.service.UserService;
import com.taylor.utils.ApiUserUtils;
import com.taylor.utils.JsonUtil;
import com.taylor.vo.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * API登录授权
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-03-23 15:31
 */
@Api(tags = "API登录授权接口")
@RestController
@RequestMapping("/api/uth")
@Slf4j
public class ApiAuthController extends BaseApi {
    @Autowired
    private UserService userService;

    @Autowired
    private SysConfigDao sysConfigDao;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 登录
     */
    @IgnoreAuth
    @ApiOperation("登录")
    @PostMapping("login_by_weixin")
    public Object loginByWeixin(@PathVariable("merchantId") Long merchantId) {
        JSONObject jsonParam = this.getJsonRequest();
        String code = "";
        if (!StringUtils.isNullOrEmpty(jsonParam.getString("code"))) {
            code = jsonParam.getString("code");
        }
        UserInfo userInfo = null;
        if (jsonParam.get("userInfo") != null) {
            userInfo = jsonParam.getObject("userInfo", UserInfo.class);
        }

        /**获取openid**/
        /**通过自定义工具类组合出小程序需要的登录凭证 code**/
        SysUserConfigEntity sysUserConfigVo = JsonUtil.getObjet(sysConfigDao.queryByKey("MINI_APP_CONFIG_KEY"),SysUserConfigEntity.class);
        String requestUrl = ApiUserUtils.getWebAccess(code,sysUserConfigVo.getAppId(),sysUserConfigVo.getSecret());
        log.info("》》》组合token为：{}", requestUrl);
        String res = restTemplate.getForObject(requestUrl, String.class);
        System.out.println("res:" + res);
        JSONObject sessionData = JSON.parseObject(res);
        System.out.println("sessionData" + JsonUtil.getJsonByObj(sessionData));

        if (sessionData == null || StringUtils.isNullOrEmpty(sessionData.getString("openid"))) {
            return R.error("登录失败");
        }
        /**验证用户信息完整性**/
        System.out.println("userInfo:" + JsonUtil.getJsonByObj(userInfo));
        Date nowTime = new Date();
        UserEntity userEntity = userService.queryByOpenId(sessionData.getString("openid"));
        if (userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setRegisterTime(nowTime);
            userEntity.setLastLoginTime(userEntity.getRegisterTime());
            userEntity.setWeixinOpenid(sessionData.getString("openid"));
            userEntity.setAvatar(userInfo.getAvatarUrl());
            /**性别 0：未知、1：男、2：女**/
            userEntity.setGender(userInfo.getGender());
            userEntity.setNickname(userInfo.getNickName());
            userEntity.setMobile(userInfo.getMobile());
            userEntity.setUserLevelId(UserLevelEnum.NORMAL.getLevelId());
            userEntity.setPoint(0L);
            userService.save(userEntity);
        } else {
            userEntity.setLastLoginTime(nowTime);
            userService.update(userEntity);
        }

        Map<String, Object> tokenMap = tokenService.createToken(userEntity.getId());
        String token = MapUtils.getString(tokenMap, "token");
        if (userInfo == null || StringUtils.isNullOrEmpty(token)) {
            return R.error("登录失败");
        }
        userInfo.setMobile(userEntity.getMobile());
        userInfo.setPoint(userEntity.getPoint());
        userInfo.setUserLevelType(userEntity.getUserLevelId());
        Map<String, Object> resultObj = new HashMap<>(0);
        resultObj.put("token", token);
        resultObj.put("userInfo", userInfo);
        resultObj.put("userId", userEntity.getId());
        return R.ok(resultObj);
    }


}
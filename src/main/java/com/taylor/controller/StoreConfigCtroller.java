package com.taylor.controller;

import com.taylor.common.CommonResponse;
import com.taylor.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/12/4 17:26
 */
@Controller
@RequestMapping("/store/")
public class StoreConfigCtroller extends BaseAction {
    @RequestMapping("config")
    @ResponseBody
    public CommonResponse<StoreConfigBean> config() {
        CommonResponse<StoreConfigBean> pageResultBeanCommonResponse = new CommonResponse<>();
        StoreConfigBean storeConfigVo = new StoreConfigBean();
        storeConfigVo.setStoreId(1143L);
        storeConfigVo.setCatering(false);
        /**员工配置**/
        EmployeeBizConfigVo employeeBizConfigVo=new EmployeeBizConfigVo();
        employeeBizConfigVo.setMerchantId(1129L);
        storeConfigVo.setEmployConfig(employeeBizConfigVo);

        EmployForConfigVo employForConfigVo=new EmployForConfigVo();
        employForConfigVo.setEmployeeId(123L);
        List<EmployForConfigVo> employForConfigVoList = new ArrayList<>();
        employForConfigVoList.add(employForConfigVo);
        storeConfigVo.setEmployees(employForConfigVoList);
        /**图**/
        storeConfigVo.setFirstPic("wwww.baidu.com.jpg");
        /**套餐id**/
        storeConfigVo.setIndustryId(1);
        storeConfigVo.setSmallPicMode(false);

        /**地理位置**/
        StoreMongo storeMongo=new StoreMongo();
        storeMongo.setId(123);
        storeConfigVo.setStoreMongo(storeMongo);


        storeConfigVo.setMerchantId(1129L);

        storeConfigVo.setSpecialIndustryStore(false);

        OnlineOrderExt onlineOrderExt=new OnlineOrderExt();
        onlineOrderExt.setExtId(1232L);
        onlineOrderExt.setIsOfferPay((byte) 0);
        storeConfigVo.setOnlineOrderExt(onlineOrderExt);

        storeConfigVo.setRecmdGoodsAndMealCards("");
        pageResultBeanCommonResponse.setData(storeConfigVo);
        return pageResultBeanCommonResponse;
    }

}

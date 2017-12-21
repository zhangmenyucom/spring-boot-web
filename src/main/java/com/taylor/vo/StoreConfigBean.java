package com.taylor.vo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2017/12/4 15:55
 */
@Data
public class StoreConfigBean implements Serializable {
    private static final long serialVersionUID = 206512773420810299L;

    /**商家id**/
    private Long merchantId;

    /**门店id**/
    private Long storeId;

    /**解决方案Id**/
    private Integer industryId;

    /**头图**/
    private String firstPic;

    private boolean isSpecialIndustryStore;

    /**员工配置**/
    private EmployeeBizConfigVo employConfig;

    /**员工列表**/
    private List<EmployForConfigVo> employees;

    /**推荐菜品**/
    private String recmdGoodsAndMealCards;

    /**订单配置**/
    private OnlineOrderExt onlineOrderExt;

    /**位置与图片**/
    private StoreMongo storeMongo;

    /**大小图模式，默认小图模式**/
    private boolean smallPicMode=true;

    /**是否美食行业**/
    private boolean isCatering;
}

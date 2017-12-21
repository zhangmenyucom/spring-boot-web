package com.taylor.vo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
public class StoreWithAddress extends StoreAddress {

    /**
     *
     */
    private static final long serialVersionUID = 2085751800057473307L;

    private String storeName;

    private String branchName;

    private String businessTypeParent;

    private String businessType;

    private String businessTypeSon;

    private String phone;

    private String businessHourStart;

    private String businessHourEnd;

    private String brief;

    private Integer status;

    private Integer wechatStatus;

    private String recommend;

    private String specialService;

    private Integer perPersonSpending;

    private Long storeVersion;

    private Long addressVersion;

    private List<String> pics;

    private String poiId;

    private String notice;

    public StoreWithAddress() {

    }

    public StoreWithAddress(Store s, StoreAddress a) {
        this.setStoreId(s.getStoreId());
        this.setMerchantId(s.getMerchantId());
        this.setStoreName(s.getStoreName());
        this.setBranchName(s.getBranchName());
        this.setBusinessTypeParent(s.getBusinessTypeParent());
        this.setBusinessType(s.getBusinessType());
        this.setBusinessTypeSon(s.getBusinessTypeSon());
        this.setBusinessHourStart(s.getBusinessHourStart());
        this.setBusinessHourEnd(s.getBusinessHourEnd());
        this.setBrief(s.getBrief());
        this.setStatus(s.getStatus());
        this.setWechatStatus(s.getWechatStatus());
        this.setRecommend(s.getRecommend());
        this.setSpecialService(s.getSpecialService());
        this.setPerPersonSpending(s.getPerPersonSpending());
        this.setPhone(s.getPhone());
        this.setPoiId(s.getPoiId());
        this.setSid(s.getSid());
        this.setNotice(s.getNotice());
        this.setAddressId(a.getAddressId());
        this.setStoreId(a.getStoreId());
        this.setProvince(a.getProvince());
        this.setCity(a.getCity());
        this.setDistrict(a.getDistrict());
        this.setAddress(a.getAddress());
        this.setMapLocationX(a.getMapLocationX());
        this.setMapLocationY(a.getMapLocationY());
        this.setAddressVersion(a.getVersion());
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName == null ? null : branchName.trim();
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getBusinessHourStart() {
        return businessHourStart;
    }

    public void setBusinessHourStart(String businessHourStart) {
        this.businessHourStart = businessHourStart == null ? null : businessHourStart.trim();
    }

    public String getBusinessHourEnd() {
        return businessHourEnd;
    }

    public void setBusinessHourEnd(String businessHourEnd) {
        this.businessHourEnd = businessHourEnd == null ? null : businessHourEnd.trim();
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend == null ? null : recommend.trim();
    }

    public String getSpecialService() {
        return specialService;
    }

    public void setSpecialService(String specialService) {
        this.specialService = specialService == null ? null : specialService.trim();
    }

    public Integer getPerPersonSpending() {
        return perPersonSpending;
    }

    public void setPerPersonSpending(Integer perPersonSpending) {
        this.perPersonSpending = perPersonSpending;
    }

    public String getBusinessTypeParent() {
        return businessTypeParent;
    }

    public void setBusinessTypeParent(String businessTypeParent) {
        this.businessTypeParent = businessTypeParent;
    }

    public Long getStoreVersion() {
        return storeVersion;
    }

    public void setStoreVersion(Long storeVersion) {
        this.storeVersion = storeVersion;
    }

    public Long getAddressVersion() {
        return addressVersion;
    }

    public void setAddressVersion(Long addressVersion) {
        this.addressVersion = addressVersion;
    }

    public WeChatShop toWeChatShop() {
        WeChatShop shop = new WeChatShop();
        WeChatShopBaseInfo base = new WeChatShopBaseInfo();
        Map<String, String> map = new IdentityHashMap<String, String>();

        base.setAddress(this.getAddress());
        base.setAvg_price(this.getPerPersonSpending() + "");
        base.setBranch_name(this.getBranchName());
        base.setBusiness_name(this.getStoreName());
//		base.setCategories(this.getBusinessTypeParent()+","+this.getBusinessType()+","+this.getBusinessTypeSon());
        base.setCity(this.getCity());
        base.setDistrict(this.getDistrict());
        base.setIntroduction(this.getBrief());
        base.setLatitude(this.getMapLocationX() + "");
        base.setLongitude(this.getMapLocationY() + "");
        base.setOffset_type("1");
        base.setOpen_time(this.getBusinessHourStart() + "-" + this.getBusinessHourEnd());
        base.setProvince(this.getProvince());
        base.setRecommend(this.getRecommend());
        base.setSid(this.getSid());
        base.setSpecial(this.getSpecialService());
        base.setTelephone(this.getPhone());
        List<String> list = new ArrayList<String>();
        String a = this.getBusinessTypeParent() + "," + this.getBusinessType();
        if (this.getBusinessTypeSon() != null && !"".equals(this.getBusinessTypeSon())) {
            a += ("," + this.getBusinessTypeSon());
        }
        list.add(a);
        base.setCategories(list);
        JSONArray jsonStrs = new JSONArray();

        base.setPoi_id(this.getPoiId());
        shop.setWeChatShopBaseInfo(base);
        return shop;

    }
    
    /**
     * 获取门店全名
     * @param spaceMark 空格符--默认一个空格
     * @return
     */
    public String getFullName(String spaceMark) {
    	if(spaceMark == null){
    		spaceMark = " ";
    	}
        String fullName = storeName;

        return fullName;
    }

}
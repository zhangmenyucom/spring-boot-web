package com.taylor.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author taylor
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Store implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1904797327221776850L;

    private Integer type;

    private Integer open;

    private Boolean sync;

    private Long storeId;

    private String sid;

    private Long merchantId;

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

    private Integer updateStatus;

    private String recommend;

    private String specialService;

    private Integer perPersonSpending;

    private String poiId;//微信店铺id

    private String failReason;//审核失败的驳回理由

    private String notice;

    private String name;

    /**门店默认岗位*/
    private String storeTitleBase;

    /**门店财务帐号**/
    private String fid;

    public Store() {

    }

    public Store(StoreWithAddress storeWithAddress) {
        this.setStoreId(storeWithAddress.getStoreId());
        this.setMerchantId(storeWithAddress.getMerchantId());
        this.setStoreName(storeWithAddress.getStoreName());
        this.setBranchName(storeWithAddress.getBranchName());
        this.setBusinessTypeParent(storeWithAddress.getBusinessTypeParent());
        this.setBusinessType(storeWithAddress.getBusinessType());
        this.setBusinessTypeSon(storeWithAddress.getBusinessTypeSon());
        this.setBusinessHourStart(storeWithAddress.getBusinessHourStart());
        this.setBusinessHourEnd(storeWithAddress.getBusinessHourEnd());
        this.setBrief(storeWithAddress.getBrief());
        this.setStatus(storeWithAddress.getStatus());
        this.setWechatStatus(storeWithAddress.getWechatStatus());
        this.setRecommend(storeWithAddress.getRecommend());
        this.setSpecialService(storeWithAddress.getSpecialService());
        this.setPerPersonSpending(storeWithAddress.getPerPersonSpending());
        this.setPhone(storeWithAddress.getPhone());
        this.setSid(storeWithAddress.getSid());
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
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

    public String getFullName() {
        String fullName = storeName;

        return fullName;
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

    /**
     * 获取门店全名
     * 例如：小吃店（宝山区）
     * @return
     */
    public String getFullNameWithBracket() {
    	String fullName = storeName;

        return fullName;
    }

    @Override
    public String toString() {
        return "Store [storeId=" + storeId + ", merchantId=" + merchantId
                + ", storeName=" + storeName + ", status=" + status + ",updateStatus=" + updateStatus
                + ", poiId=" + poiId + ", failReason=" + failReason + "]";
    }

}
package com.taylor.vo;

import com.taylor.common.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class StoreAddress extends Base {
    private Long addressId;

    private Long merchantId;

    private Long storeId;

    private String sid;

    private String province;
    
    private String city;

    private String district;
    
    private String address;

    private float mapLocationX;

    private float mapLocationY;
    
    public StoreAddress(){
    	
    }
    
    public StoreAddress(StoreWithAddress storeWithAddress){
    	this.setAddressId(storeWithAddress.getAddressId());
    	this.setMerchantId(storeWithAddress.getMerchantId());
    	this.setStoreId(storeWithAddress.getStoreId());
    	this.setProvince(storeWithAddress.getProvince());
    	this.setCity(storeWithAddress.getCity());
    	this.setDistrict(storeWithAddress.getDistrict());
    	this.setAddress(storeWithAddress.getAddress());
    	this.setMapLocationX(storeWithAddress.getMapLocationX());
    	this.setMapLocationY(storeWithAddress.getMapLocationY());
    	this.setVersion(storeWithAddress.getAddressVersion());
        this.setSid(storeWithAddress.getSid());
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public float getMapLocationX() {
        return mapLocationX;
    }

    public void setMapLocationX(float mapLocationX) {
        this.mapLocationX = mapLocationX;
    }

    public float getMapLocationY() {
        return mapLocationY;
    }

    public void setMapLocationY(float mapLocationY) {
        this.mapLocationY = mapLocationY;
    }

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
    
    
}
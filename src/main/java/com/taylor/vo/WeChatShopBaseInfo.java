package com.taylor.vo;


import com.alibaba.fastjson.JSONArray;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class WeChatShopBaseInfo {
	private String sid;
	private String business_name;
	private String branch_name;
	private String province;
	private String city;
	private String district;
	private String address;
	private String telephone;
	private List categories;
	private String offset_type;
	private String longitude;
	private String latitude;
	private JSONArray photo_list;
	private String recommend;
	private String special;
	private String introduction;
	private String open_time;
	private String avg_price;
	
	private String poi_id;
	
	private Map<String,String> map;
	
	

}

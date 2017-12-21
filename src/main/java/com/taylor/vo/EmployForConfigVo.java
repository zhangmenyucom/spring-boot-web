package com.taylor.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Kowalski on 2017/3/14
 */
@Data
public class EmployForConfigVo implements Serializable {

    private static final long serialVersionUID = 914872166633171287L;

    private Long merchantId;
    private Long storeId;
    private Long employeeId; //员工ID
    private String name;       //名称
    private String headImage;  //员工头像
    private String nickName;   //昵称
    private String title;      //岗位
    private String phone;      //电话
    private Integer status;     //员工状态

}

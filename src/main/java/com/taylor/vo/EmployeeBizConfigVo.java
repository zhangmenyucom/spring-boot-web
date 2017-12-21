package com.taylor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Kowalski on 2017/3/10
 * Updated by Kowalski on 2017/3/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeBizConfigVo implements Serializable {
    private static final long serialVersionUID = -8917974736461327257L;

    private Long employeeBizId;//ID(暂未使用 17-03-14)

    private Long merchantId; //商户ID
    private Long storeId;    //门店ID
    private String bizName;    //名称
    private Integer status;     //状态
    private List<EmployForConfigVo> employees;  //员工列表

}

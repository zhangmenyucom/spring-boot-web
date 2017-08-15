package com.taylor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class GoodsTastesUnit implements Serializable {
    private static final long serialVersionUID = -2060225306040574375L;
    private Long goodsTastesUnitId;
    private Long merchantId;
    private String tastesName;
    private String thirdTastesId;
    private Integer status;
    private String createTime;
    private String updateTime;
    private Integer version;
}
package com.taylor.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class GoodsTastesUnitQueryBean {
    private Long goodsTastesUnitId;
    private Long merchantId;
    private String tastesName;
    private String thirdTastesId;
    private Integer status;
    private Integer version;
    private String searchWord;
}
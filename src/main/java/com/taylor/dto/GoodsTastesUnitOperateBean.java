package com.taylor.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@ToString
public class GoodsTastesUnitOperateBean {
    private Long id;
    private Long merchantId;
    private String tastesName;
    private String thirdTastesId;
    private List<Long> ids;
}
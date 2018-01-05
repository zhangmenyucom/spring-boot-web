package com.taylor.common;

import com.taylor.entity.stock.MashData;
import lombok.Data;

import java.util.List;

/**
 * @author taylor
 */
@Data
public class CommonResponse {
	private Integer errorNo;
	private String errorMsg;
	private List<MashData> mashData;
}

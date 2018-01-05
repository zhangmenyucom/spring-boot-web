package com.taylor.common;

import lombok.Data;

/**
 * @author taylor
 */
@Data
public class CommonResponse<T> {
	private Integer errorNo;
	private String errorMsg;
	private T mashData;
}

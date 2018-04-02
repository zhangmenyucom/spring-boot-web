package com.taylor.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author taylor
 */
@Data
public class CommonResponse implements Serializable {
	private static final long serialVersionUID = 3256823830099426928L;
	protected Integer errorNo;
	protected String errorMsg;
}

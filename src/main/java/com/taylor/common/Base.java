package com.taylor.common;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Base implements Serializable {

	private static final long serialVersionUID = -2777782859639223907L;

	private Date createTime;

    private Date updateTime;

    private int page=1;
    
    private Long version = 1L;
    
    private int pageNumber = 1;
    
    private int pagesize = 10;
    
    private Long pageTotal = 0L;
}

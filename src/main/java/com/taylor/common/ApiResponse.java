package com.taylor.common;

import lombok.Data;

/**
 * @author xiaolu.zhang
 * @desc:
 * @date: 2018/4/2 17:25
 */
@Data
public class ApiResponse<T> extends CommonResponse {
    private static final long serialVersionUID = 8981941807853203639L;
    private T data;

    public ApiResponse(){
    }
    public ApiResponse(int errorCode){
        this.errorNo=errorCode;
    }
}

package com.taylor.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * ${author} on 2018/8/6.
 *
 * @author Taylor
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class ApiResult<T> {

    @ApiModelProperty(value = "业务码", dataType = "java.lang.Integer", required = true, position = 1)
    private Integer errorCode;

    @ApiModelProperty(value = "业务码说明", required = true, example = "成功", position = 2)
    private String errorMessage;

    @ApiModelProperty(value = "业务数据", position = 3)
    private T data;

    public ApiResult<T> failure(String message) {
        return new ApiResult<T>().setErrorCode(ErrorCode.FAILED).setErrorMessage(message);
    }

    public ApiResult<T> ok(T data) {
        return new ApiResult<T>().setErrorCode(ErrorCode.SUCCESS).setErrorMessage("sucess").setData(data);
    }
}

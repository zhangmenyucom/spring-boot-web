package com.taylor.common.exception;

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
@NoArgsConstructor
@Accessors(chain = true)
public class ServiceException extends Exception {
    private static final long serialVersionUID = 1730015230338311522L;

    private Integer errorCode;
    private String errorMessage;
}

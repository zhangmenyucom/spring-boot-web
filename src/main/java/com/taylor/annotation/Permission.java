package com.taylor.annotation;

import com.taylor.common.UserLevelEnum;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 忽略Token验证
 *
 * @author taylor
 * @email 516195940@qq.com
 * @date 2017-03-23 15:44
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {

    @AliasFor("roles")
    UserLevelEnum[] value() default {};

}

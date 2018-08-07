package com.taylor.data.common;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * 在全量更新里忽略
 *
 * @author dong.zhou
 * @since 2018/6/5 上午2:46
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(FIELD)
public @interface FullUpdateIgnore {
}

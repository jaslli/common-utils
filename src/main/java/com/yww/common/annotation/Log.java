package com.yww.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *      用于标记记录用户操作日志的方法
 * </P>
 *
 * @author yww
 * @since 2023/3/4
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    String value() default "";

    boolean save() default false;
}
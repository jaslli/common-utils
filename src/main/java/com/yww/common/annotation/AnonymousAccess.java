package com.yww.common.annotation;

import java.lang.annotation.*;

/**
 * <p>
 *      用于标记匿名访问的接口
 * </P>
 *
 * @author yww
 * @since 2023/3/4
 */
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnonymousAccess {
}

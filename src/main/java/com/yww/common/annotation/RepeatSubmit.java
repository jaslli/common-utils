package com.yww.common.annotation;


import java.lang.annotation.*;

/**
 * <p>
 *      幂等性注解
 * </P>
 *
 * @author yww
 * @since 2023/4/9
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {

    /**
     * 防重复操作过期时间,默认2秒
     */
    long expireTime() default 2;

    /**
     * redis的key前缀
     */
    String prefix() default "yww_key:";

    /**
     * 防重复操作的错误信息
     */
    String errMsg() default "请勿重复提交！";

}

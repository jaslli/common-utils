package com.yww.common.aop;

import cn.hutool.core.util.StrUtil;
import com.yww.common.annotation.RepeatSubmit;
import com.yww.common.exception.BusinessException;
import com.yww.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *      幂等性切面
 * </P>
 *
 * @author yww
 * @since 2023/4/9
 */
@Slf4j
public class RepeatSubmitAspect {

    @Resource
    RedisUtil redisUtil;

    /**
     * 声明切入点
     */
    @Pointcut("@annotation(com.yww.common.annotation.RepeatSubmit)")
    private void pointCut() {}

    /**
     * 处理请求前通知
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取注解信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);

        // 获取Token，生成redis的key
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request;
        String token = "";
        String url = "";
        if (attributes != null) {
            request = attributes.getRequest();
            token = request.getHeader("Authorization");
            if (StrUtil.isBlank(token)) {
                throw new BusinessException("请先登录之后在操作！");
            }
            url = request.getRequestURI();
        }
        /*
          生成redis的key
          目前为 前缀 + url + token
         */
        String redisKey = annotation.prefix() + url + token;
        log.info("==========redisKey ====== {}", redisKey);

        // 如果key存在，则报错
        if (redisUtil.hasKey(redisKey)) {
            throw new BusinessException(annotation.errMsg());
        }

        // 处理key信息
        redisUtil.setStr(redisKey, redisKey, annotation.expireTime(), TimeUnit.MINUTES);
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            // 请求出错则删除key信息，然后抛出异常
            redisUtil.deleteStr(redisKey);
            throw new Throwable(throwable);
        }

    }

}

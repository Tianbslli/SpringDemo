package com.tianbsli.aop;

import com.alibaba.fastjson.JSONObject;
import com.tianbsli.annotation.PrintLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


/**
 * @author Jorsh
 * @version 1.0
 * @date 2020/3/22 6:38 下午
 */

@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    //@Pointcut("execution(* com.tianbsli.*.*(..))")
    @Pointcut("@annotation(com.tianbsli.annotation.PrintLog)")
    public void controllerLog() {}

    @Before("controllerLog()")
    public void before(JoinPoint joinPoint){
        printRequestInfo(joinPoint);
    }

    @AfterReturning(returning = "object",pointcut = "controllerLog()")
    public void doAfterReturning(JoinPoint joinPoint,Object object) throws Exception {
        logger.info("返回值：{}", object.toString());
    }


    private void printRequestInfo(JoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info("请求IP：{}", request.getRemoteAddr());
        logger.info("请求路径：{}", request.getRequestURL());
        logger.info("请求方式：{}", request.getMethod());
        logger.info("目标方法名：{}", joinPoint.getSignature().getName());
        logger.info("目标方法所属类：{}", joinPoint.getSignature().getDeclaringTypeName());
        logger.info("目标方法声明类型：{}", Modifier.toString(joinPoint.getSignature().getModifiers()));
        logger.info("请求参数：{}", JSONObject.toJSONString(request.getParameterMap()));
    }

}

package com.taylor.aop.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/**
 * @author Taylor  on 2018/8/6.
 */
@Aspect
@Slf4j
public class LogInfoAspect {
    private static final String[] ACCPET_ENV = {"dev", "test"};
    private final Environment env;

    public LogInfoAspect(Environment env) {
        this.env = env;
    }

    @Pointcut("@annotation(com.taylor.annotation.LogInfo)")
    public void logInfoPointcut() {
    }

    @Before("logInfoPointcut()")
    public void logBefore(JoinPoint joinPoint) throws Throwable {
        log.debug("进入日志区域");
    }

    @AfterThrowing(pointcut = "logInfoPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        if (env.acceptsProfiles(ACCPET_ENV)) {
            log.error("Exception in {}.{}() with cause = \'{}\' and exception = \'{}\'", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL", e.getMessage(), e);
        } else {
            log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
        }
    }

    @Around("logInfoPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }
    }

    @After("logInfoPointcut()")
    public void logAfter(JoinPoint joinPoint) throws Throwable {
        log.debug("退出日志区域");
    }

    @AfterReturning("logInfoPointcut()")
    public void afterReturning(JoinPoint joinPoint) {

    }
}
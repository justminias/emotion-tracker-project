package com.emotion.emotiontracker.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
@Log4j2
public class RepositoryMethodsAspect {

    @Pointcut("execution(* com.emotion.emotiontracker.repository..*(..))")
    public void repositoryMethods() {
    }

    @Around("repositoryMethods()")
    public Object logDuration(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        log.info("Method: {}.{} completed in: {} ms.",
                proceedingJoinPoint.getSignature(),
                proceedingJoinPoint.getSignature().getName(),
                duration);
        return result;
    }
}

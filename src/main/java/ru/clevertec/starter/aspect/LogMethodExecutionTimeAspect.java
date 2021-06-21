package ru.clevertec.starter.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class LogMethodExecutionTimeAspect {

    @Pointcut("@annotation(ru.clevertec.starter.service.LogMethodExecutionTime)")
    public void executeTiming(){}

    @Around("executeTiming()")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final long startTime = System.currentTimeMillis();
        final Object proceed = proceedingJoinPoint.proceed();
        final long executionTime = System.currentTimeMillis() - startTime;

        log.warn(proceedingJoinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}

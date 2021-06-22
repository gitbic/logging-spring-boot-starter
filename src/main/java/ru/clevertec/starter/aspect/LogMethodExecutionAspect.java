package ru.clevertec.starter.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import static ru.clevertec.starter.constants.Constants.COMMON_DEFAULT_POINTCUT;

@Slf4j
@Aspect
public class LogMethodExecutionAspect {

    @Pointcut(COMMON_DEFAULT_POINTCUT)
    public void getDefaultPointcut() {
    }

    @Before("getDefaultPointcut()")
    public void logMethodBeforeExecution(JoinPoint point) {
        log.warn("Before method executing: " + point.getSignature().toString());
    }

    @AfterReturning(pointcut = "getDefaultPointcut()", returning = "result")
    public void logMethodAfterExecution(JoinPoint point, Object result) {
        log.warn("After method executing: " + point.getSignature().toString() +
                "==" + result + "====" + point);
    }

    @Around("getDefaultPointcut()")
    public Object logMethodDuringExecution(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final long startTime = System.currentTimeMillis();
        final Object proceed = proceedingJoinPoint.proceed();
        final long executionTime = System.currentTimeMillis() - startTime;

        log.warn("During method execution: " + proceedingJoinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}

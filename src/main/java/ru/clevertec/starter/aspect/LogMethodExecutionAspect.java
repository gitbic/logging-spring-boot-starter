package ru.clevertec.starter.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import ru.clevertec.starter.constants.Description;

import java.util.Arrays;

import static ru.clevertec.starter.constants.Constants.COMMON_DEFAULT_POINTCUT;

@Slf4j
@Aspect
public class LogMethodExecutionAspect {

    @Pointcut(COMMON_DEFAULT_POINTCUT)
    public void getDefaultPointcut() {
    }


    @Before("getDefaultPointcut()")
    public void logMethodBeforeExecution(JoinPoint joinPoint) {
        log.warn(Description.BEFORE_METHOD_EXECUTING +
                Description.METHOD_SIGNATURE + joinPoint.getSignature().toString() +
                Description.METHOD_ARGUMENTS + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "getDefaultPointcut()", returning = "returningValues")
    public void logMethodAfterExecution(JoinPoint joinPoint, Object returningValues) {
        log.warn(Description.AFTER_METHOD_EXECUTING +
                Description.METHOD_SIGNATURE + joinPoint.getSignature().toString() +
                Description.METHOD_ARGUMENTS + Arrays.toString(joinPoint.getArgs()) +
                Description.METHOD_RETURNING_VALUES + returningValues);
    }

    @Around("getDefaultPointcut()")
    public Object logMethodDuringExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        final long startTime = System.currentTimeMillis();
        final Object proceed = joinPoint.proceed();
        final long executionTime = System.currentTimeMillis() - startTime;

        log.warn(Description.DURING_METHOD_EXECUTING +
                Description.METHOD_SIGNATURE + joinPoint.getSignature().toString() +
                Description.METHOD_EXECUTING_TIME + executionTime);

        return proceed;
    }
}

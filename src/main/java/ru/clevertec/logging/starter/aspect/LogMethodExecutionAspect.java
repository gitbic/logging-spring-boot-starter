package ru.clevertec.logging.starter.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import ru.clevertec.logging.starter.constants.LoggingMessage;
import ru.clevertec.logging.starter.constants.Pointcuts;

import java.util.Arrays;

@Slf4j
@Aspect
public class LogMethodExecutionAspect {

    @Pointcut(Pointcuts.COMMON_DEFAULT_POINTCUT)
    public void getDefaultPointcut() {
    }


    @Before("getDefaultPointcut()")
    public void logMethodBeforeExecution(JoinPoint joinPoint) {

        log.warn(LoggingMessage.BEFORE_METHOD_EXECUTION_MESSAGE,
                joinPoint.getSignature().toString(), Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "getDefaultPointcut()", returning = "returningValues")
    public void logMethodAfterExecution(JoinPoint joinPoint, Object returningValues) {

        log.warn(LoggingMessage.AFTER_METHOD_EXECUTION_MESSAGE,
                joinPoint.getSignature().toString(), Arrays.toString(joinPoint.getArgs()), returningValues);
    }

    @Around("getDefaultPointcut()")
    public Object logMethodDuringExecution(ProceedingJoinPoint joinPoint) throws Throwable {

        final long startTime = System.currentTimeMillis();
        final Object proceed = joinPoint.proceed();
        final long methodExecutionTime = System.currentTimeMillis() - startTime;

        log.warn(LoggingMessage.DURING_METHOD_EXECUTION_MESSAGE,
                joinPoint.getSignature().toString(), methodExecutionTime);
        return proceed;
    }

    @AfterThrowing(pointcut = "getDefaultPointcut()", throwing = "exception")
    public void logMethodAfterThrowingException(JoinPoint joinPoint, Exception exception) {

        log.error(LoggingMessage.AFTER_METHOD_THROWING_EXCEPTION_MESSAGE,
                joinPoint.getSignature().toString(), exception.getMessage());
    }
}

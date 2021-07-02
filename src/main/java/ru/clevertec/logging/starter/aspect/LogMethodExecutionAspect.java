package ru.clevertec.logging.starter.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.aop.support.AopUtils;
import ru.clevertec.logging.starter.config.constants.LoggingMessage;
import ru.clevertec.logging.starter.config.constants.PointcutPattern;
import ru.clevertec.logging.starter.entity.AspectProperties;
import ru.clevertec.logging.starter.enums.ApiLayer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;

@Slf4j
@Aspect
public class LogMethodExecutionAspect {

    private final AspectProperties aspectProperties;

    public LogMethodExecutionAspect(AspectProperties aspectProperties) throws NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        this.aspectProperties = aspectProperties;
        changePointcut();
    }

//    @Pointcut("")
    @Pointcut(PointcutPattern.DEFAULT_POINTCUT)
    public void getPointcut() {
    }

    private void changePointcut() throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        String newPointcutPattern = aspectProperties.getPattern();
        Method getPointcutMethod = this.getClass().getDeclaredMethod("getPointcut");
        Pointcut pointcut = getPointcutMethod.getAnnotation(Pointcut.class);
//        System.out.println("pointcut value: " + pointcut.value());

        InvocationHandler invocationHandler = Proxy.getInvocationHandler(pointcut);
        Field field = invocationHandler.getClass().getDeclaredField("memberValues");
        field.setAccessible(true);

        Map<String, Object> memberValues = (Map<String, Object>) field.get(invocationHandler);
        memberValues.put("value", newPointcutPattern);

        Pointcut newPointcut = getPointcutMethod.getAnnotation(Pointcut.class);
        System.out.println("new pointcut value: " + newPointcut.value());
    }

    @Before("getPointcut()")
    public void logMethodBeforeExecution(JoinPoint joinPoint) throws NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
//        changePointcut();

        log.warn(LoggingMessage.BEFORE_METHOD_EXECUTION_MESSAGE,
                joinPoint.getSignature().toString(), Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "getPointcut()", returning = "returningValues")
    public void logMethodAfterExecution(JoinPoint joinPoint, Object returningValues) {

        log.warn(LoggingMessage.AFTER_METHOD_EXECUTION_MESSAGE,
                joinPoint.getSignature().toString(), Arrays.toString(joinPoint.getArgs()), returningValues);
    }

    @Around("getPointcut()")
    public Object logMethodDuringExecution(ProceedingJoinPoint joinPoint) throws Throwable {

//        String newPointcutPattern = aspectProperties.getPattern();
//        Method getPointcutMethod = this.getClass().getDeclaredMethod("getPointcut");
//        Pointcut pointcut = getPointcutMethod.getAnnotation(Pointcut.class);
//        System.out.println(pointcut.value());
//
//        InvocationHandler invocationHandler = Proxy.getInvocationHandler(pointcut);
//        Field field = invocationHandler.getClass().getDeclaredField("memberValues");
//        field.setAccessible(true);
//
//        Map<String, Object> memberValues = (Map<String, Object>) field.get(invocationHandler);
//        memberValues.put("value", newPointcutPattern);

        final long startTime = System.currentTimeMillis();
        final Object proceed = joinPoint.proceed();
        final long methodExecutionTime = System.currentTimeMillis() - startTime;
        log.warn(LoggingMessage.DURING_METHOD_EXECUTION_MESSAGE,
                joinPoint.getSignature().toString(), methodExecutionTime);
        return proceed;
    }

    @AfterThrowing(pointcut = "getPointcut()", throwing = "exception")
    public void logMethodAfterThrowingException(JoinPoint joinPoint, Exception exception) {

        log.error(LoggingMessage.AFTER_METHOD_THROWING_EXCEPTION_MESSAGE,
                joinPoint.getSignature().toString(), exception.getMessage());
    }


}

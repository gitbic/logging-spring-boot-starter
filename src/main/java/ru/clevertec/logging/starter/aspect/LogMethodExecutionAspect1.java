package ru.clevertec.logging.starter.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import ru.clevertec.logging.starter.config.constants.LoggingMessage;
import ru.clevertec.logging.starter.config.constants.PointcutPattern;
import ru.clevertec.logging.starter.entity.AspectProperties;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;

@Slf4j
@Aspect
public class LogMethodExecutionAspect1 extends LogMethodExecutionAspect{

    private final AspectProperties aspectProperties;

    public LogMethodExecutionAspect1(AspectProperties aspectProperties) throws NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        this.aspectProperties = aspectProperties;
        changePointcut();
    }

//    @Pointcut("ru.clevertec.logging.starter.aspect.AspectPointcut.getServicePointcut()")
//    @Pointcut(PointcutPattern.SERVICE_POINTCUT)
    @Pointcut("")
    public void getPointcut() {
    }

    public void changePointcut() throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        String newPointcutPattern = aspectProperties.getPattern();

        Method getPointcutMethod = this.getClass().getDeclaredMethod("getPointcut");
        Annotation pointcut = getPointcutMethod.getAnnotation(Pointcut.class);
//        System.out.println("Old pointcut value: " + pointcut.value());

        InvocationHandler invocationHandler = Proxy.getInvocationHandler(pointcut);
        Field field = invocationHandler.getClass().getDeclaredField("memberValues");
        field.setAccessible(true);

        Map<String, Object> annotationValues = (Map<String, Object>) field.get(invocationHandler);
        System.out.println("Annotation value before: " + annotationValues);
        annotationValues.put("value", newPointcutPattern);
        System.out.println("Annotation value after: " + annotationValues);

        Pointcut newPointcut = getPointcutMethod.getAnnotation(Pointcut.class);
        System.out.println("New pointcut value: " + newPointcut.value());
    }

    @Before("getPointcut()")
    public void logMethodBeforeExecution(JoinPoint joinPoint) throws NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
//        System.out.println(aspectProperties);
        System.out.println("========= This class hash code: " + this.hashCode());
        System.out.println("============= Layer: " + aspectProperties.getLayer() + "; Pattern: " + aspectProperties.getPattern());

        log.warn(LoggingMessage.BEFORE_METHOD_EXECUTION_MESSAGE,
                joinPoint.getSignature().toString(), Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "getPointcut()", returning = "returningValues")
    public void logMethodAfterExecution(JoinPoint joinPoint, Object returningValues) {

        if (aspectProperties.getLoggingFormat().isReturnValuePrints()) {
            returningValues = "";
            //!!!!!!!!!!!!!!
        }

        log.warn(LoggingMessage.AFTER_METHOD_EXECUTION_MESSAGE,
                joinPoint.getSignature().toString(), Arrays.toString(joinPoint.getArgs()), returningValues);

    }

    @Around("getPointcut()")
    public Object logMethodDuringExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        final Object proceed = joinPoint.proceed();

        if (aspectProperties.getLoggingFormat().isMethodExecutingTimePrints()) {
            final long startTime = System.currentTimeMillis();
            final long methodExecutionTime = System.currentTimeMillis() - startTime;
            log.warn(LoggingMessage.DURING_METHOD_EXECUTION_MESSAGE,
                    joinPoint.getSignature().toString(), methodExecutionTime);
        }

        return proceed;
    }

    @AfterThrowing(pointcut = "getPointcut()", throwing = "exception")
    public void logMethodAfterThrowingException(JoinPoint joinPoint, Exception exception) {

        log.error(LoggingMessage.AFTER_METHOD_THROWING_EXCEPTION_MESSAGE,
                joinPoint.getSignature().toString(), exception.getMessage());
    }


}

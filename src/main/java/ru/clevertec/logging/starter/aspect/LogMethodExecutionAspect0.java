package ru.clevertec.logging.starter.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import ru.clevertec.logging.starter.config.constants.LoggingMessage;
import ru.clevertec.logging.starter.entity.AspectProperties;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;


@Slf4j
@Aspect
public class LogMethodExecutionAspect0 {

    private final AspectProperties aspectProperties;
    private final String pointcutPattern = "execution(* ru.clevertec..*repository*..*(..))";

    public LogMethodExecutionAspect0(AspectProperties aspectProperties) throws NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        this.aspectProperties = aspectProperties;
//        changePointcut();
    }


    //    @Pointcut("ru.clevertec.logging.starter.aspect.AspectPointcut.getControllerPointcut()")
    @Pointcut(pointcutPattern)
    //    @Pointcut("execution(* ru.clevertec..*repository*..*(..))")
    public void getPointcut() {
    }


    public void changePointcut() throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        String newPointcutPattern = aspectProperties.getPattern();

        Method getPointcutMethod = this.getClass().getDeclaredMethod("getPointcut");
        Pointcut oldPointcut = getPointcutMethod.getAnnotation(Pointcut.class);
        System.out.println("Old pointcut value: " + oldPointcut.value());

//        Field value = Pointcut.class.getDeclaredField("value");
//        value.set(String.class, "член");

//        Method getPointcutMethod2 = this.getClass().getDeclaredMethod("getPointcut");
//        Pointcut oldPointcut2 = getPointcutMethod2.getAnnotation(Pointcut.class);
//        System.out.println("Old pointcut value2: " + oldPointcut2.value());

        InvocationHandler invocationHandler = Proxy.getInvocationHandler(oldPointcut);


//        //--
//        Map<String, Object> myValueMap = new LinkedHashMap<>();
//        myValueMap.put("value", newPointcutPattern);
//        Class<? extends Annotation> myAnnotationType = oldPointcut.annotationType();
//        InvocationHandler invocationHandler = new MyAnnotationInvocationHandler(myAnnotationType, myValueMap);
//        //--
//
//
//
//        final Proxy oldPointcutProxy = (Proxy) oldPointcut;
//
//        //--
//        ClassLoader classLoader = oldPointcut.getClass().getClassLoader();
//        Class<?>[] interfaces = oldPointcut.getClass().getInterfaces();
//        Object myProxy = Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
//
//        System.out.println("Fields of oldPointcutProxy: " + Arrays.toString(oldPointcutProxy.getClass().getDeclaredFields()));
//
//
//        //--
//        System.out.println("OldPointcutProxy: " + oldPointcutProxy);
//        System.out.println("Hash code oldPointcutProxy: " + oldPointcutProxy.hashCode());
        System.out.println("Invocation handler: " + invocationHandler);
        System.out.println("Hash code oldPointcut: " + oldPointcut.hashCode());
        System.out.println("Hash code this class: " + this.hashCode());
        System.out.println("Hash code invocation handler: " + invocationHandler.hashCode());

        Field field = invocationHandler.getClass().getDeclaredField("memberValues");
        field.setAccessible(true);

        Map<String, Object> annotationValues = (Map<String, Object>) field.get(invocationHandler);
        System.out.println("Annotation value before: " + annotationValues);
        annotationValues.put("value", newPointcutPattern);
        System.out.println("Annotation value after: " + annotationValues);

        Pointcut newPointcut = getPointcutMethod.getAnnotation(Pointcut.class);
        System.out.println("New pointcut value: " + newPointcut.value());
        System.out.println("============= Layer: " + aspectProperties.getLayer() + "; Pattern: " + aspectProperties.getPattern());
    }

    @Before("getPointcut()")
    public void logMethodBeforeExecution(JoinPoint joinPoint) throws NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
//        System.out.println(aspectProperties);


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

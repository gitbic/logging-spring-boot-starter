package ru.clevertec.logging.starter.config.configuration;

import lombok.SneakyThrows;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.joor.Reflect;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.logging.starter.aspect.LogMethodExecutionAspect0;
import ru.clevertec.logging.starter.config.constants.Constants;
import ru.clevertec.logging.starter.entity.AspectProperties;
import ru.clevertec.logging.starter.entity.LoggingServiceProperties;
import ru.clevertec.logging.starter.entity.MyPrototype;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

@Configuration
public class BeanConfig implements BeanFactoryPostProcessor {

    @SneakyThrows
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.registerSingleton("LKnvja", new ModelMapper());

        beanFactory.registerSingleton("myPrototype1", new MyPrototype("Hello from prototype1"));
        beanFactory.registerSingleton("myPrototype2", new MyPrototype("Hello from prototype2"));


        LoggingServiceProperties defaultLoggingServiceProperties =
                beanFactory.getBean(Constants.DEFAULT_LOGGING_SERVICE_PROPERTIES_BEAN_NAME, LoggingServiceProperties.class);
        System.out.println(defaultLoggingServiceProperties);

        AspectProperties aspectProperties0 = defaultLoggingServiceProperties.getAspectsProperties().get(0);
        AspectProperties aspectProperties1 = defaultLoggingServiceProperties.getAspectsProperties().get(1);

//        LogMethodExecutionAspect0 singletonObject0 = new LogMethodExecutionAspect0(aspectProperties0);
//        changePointcut(singletonObject0, "execution(* ru.clevertec..*controller*..*(..))");
        beanFactory.registerSingleton("LogMethodExecutionAspect0", setPointcut(aspectProperties0, "execution(* ru.clevertec..*controller*..*(..))"));

//        LogMethodExecutionAspect0 singletonObject1 = new LogMethodExecutionAspect0(aspectProperties1);
//        changePointcut(singletonObject1, "execution(* ru.clevertec..*service*..*(..))");
        beanFactory.registerSingleton("LogMethodExecutionAspect1", setPointcut(aspectProperties1, "execution(* ru.clevertec..*service*..*(..))"));

//        LogMethodExecutionAspect0 logMethodExecutionAspect0 = beanFactory.getBean("LogMethodExecutionAspect0", LogMethodExecutionAspect0.class);
//        LogMethodExecutionAspect0 logMethodExecutionAspect1 = beanFactory.getBean("LogMethodExecutionAspect1", LogMethodExecutionAspect0.class);


        System.out.println("!!!");

    }


    public LogMethodExecutionAspect0 setPointcut(AspectProperties aspectProperties, String pointcut) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        Constructor<? extends LogMethodExecutionAspect0> declaredConstructor = LogMethodExecutionAspect0.class.getDeclaredConstructor(AspectProperties.class);
        declaredConstructor.setAccessible(true);

        Field pointcutPattern = LogMethodExecutionAspect0.class.getDeclaredField("pointcutPattern");
        pointcutPattern.setAccessible(true);
        FieldUtils.removeFinalModifier(pointcutPattern);
        pointcutPattern.set(logMethodExecutionAspect0, pointcut);

        LogMethodExecutionAspect0 logMethodExecutionAspect0 = declaredConstructor.newInstance(aspectProperties);



        return logMethodExecutionAspect0;
    }

    public void changePointcut(LogMethodExecutionAspect0 logMethodExecutionAspect0, String pointcut) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        Method DECLARED_ANNOTATIONS_METHOD;
        DECLARED_ANNOTATIONS_METHOD = Method.class.getDeclaredMethod("getDeclaredAnnotations");
        DECLARED_ANNOTATIONS_METHOD.setAccessible(true);

        Field DECLARED_ANNOTATIONS_FIELD;
        DECLARED_ANNOTATIONS_FIELD = Field.class.getDeclaredField("declaredAnnotations");
        DECLARED_ANNOTATIONS_FIELD.setAccessible(true);

        Object annotationData = DECLARED_ANNOTATIONS_METHOD.invoke(logMethodExecutionAspect0.getClass().getDeclaredMethod("getPointcut"));

//        Field annotations = annotationData.getClass().getDeclaredField("declaredAnnotations");
//        annotations.setAccessible(true);
//        Map<Class<? extends Annotation>, Annotation> map =
//                (Map<Class<? extends Annotation>, Annotation>) annotations.get(annotationData);
//        map.put(Pointcut.class, new DynamicPointCut(pointcut));

        Map<Class<? extends Annotation>, Annotation> declaredAnnotations =
                (Map<Class<? extends Annotation>, Annotation>) DECLARED_ANNOTATIONS_FIELD.get(logMethodExecutionAspect0);

//        Method method = Class.class.getDeclaredMethod("annotationData", null);
//        method.setAccessible(true);
//        logMethodExecutionAspect0.getClass().getDeclaredMethod("getPointcut");
//        Object annotationData = method.invoke(logMethodExecutionAspect0.getClass().getDeclaredMethod("getPointcut"));

//        Method annotations = annotationData.getClass().getDeclaredMethod("getPointcut()");
////        annotations.setAccessible(true);
//        Map<Class<? extends Annotation>, Annotation> map =
//                (Map<Class<? extends Annotation>, Annotation>) annotations.get(annotationData);
//        map.put(Pointcut.class, new DynamicPointCut(pointcut));


//        Method getPointcutMethod = logMethodExecutionAspect0.getClass().getDeclaredMethod("getPointcut");
//        Pointcut oldPointcut = getPointcutMethod.getAnnotation(Pointcut.class);
//        System.out.println("Old pointcut value: " + oldPointcut.value());
//
////        Field value = Pointcut.class.getDeclaredField("value");
////        value.set(String.class, "член");
//
////        Method getPointcutMethod2 = this.getClass().getDeclaredMethod("getPointcut");
////        Pointcut oldPointcut2 = getPointcutMethod2.getAnnotation(Pointcut.class);
////        System.out.println("Old pointcut value2: " + oldPointcut2.value());
//
//        InvocationHandler invocationHandler = Proxy.getInvocationHandler(oldPointcut);
//
//
////        //--
////        Map<String, Object> myValueMap = new LinkedHashMap<>();
////        myValueMap.put("value", newPointcutPattern);
////        Class<? extends Annotation> myAnnotationType = oldPointcut.annotationType();
////        InvocationHandler invocationHandler = new MyAnnotationInvocationHandler(myAnnotationType, myValueMap);
////        //--
////
////
////
////        final Proxy oldPointcutProxy = (Proxy) oldPointcut;
////
////        //--
////        ClassLoader classLoader = oldPointcut.getClass().getClassLoader();
////        Class<?>[] interfaces = oldPointcut.getClass().getInterfaces();
////        Object myProxy = Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
////
////        System.out.println("Fields of oldPointcutProxy: " + Arrays.toString(oldPointcutProxy.getClass().getDeclaredFields()));
////
////
////        //--
////        System.out.println("OldPointcutProxy: " + oldPointcutProxy);
////        System.out.println("Hash code oldPointcutProxy: " + oldPointcutProxy.hashCode());
//        System.out.println("Invocation handler: " + invocationHandler);
//        System.out.println("Hash code oldPointcut: " + oldPointcut.hashCode());
//        System.out.println("Hash code this class: " + this.hashCode());
//        System.out.println("Hash code invocation handler: " + invocationHandler.hashCode());
//
//        Field field = invocationHandler.getClass().getDeclaredField("memberValues");
//        field.setAccessible(true);
//
//        Map<String, Object> annotationValues = (Map<String, Object>) field.get(invocationHandler);
//        System.out.println("Annotation value before: " + annotationValues);
//        annotationValues.put("value", newPointcutPattern);
//        System.out.println("Annotation value after: " + annotationValues);
//
//        Pointcut newPointcut = getPointcutMethod.getAnnotation(Pointcut.class);
//        System.out.println("New pointcut value: " + newPointcut.value());
//        System.out.println("============= Layer: " + aspectProperties.getLayer() + "; Pattern: " + aspectProperties.getPattern());
    }
}

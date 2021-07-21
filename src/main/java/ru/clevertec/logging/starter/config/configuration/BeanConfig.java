package ru.clevertec.logging.starter.config.configuration;

import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.logging.starter.aspect.LogMethodExecutionAspect;
import ru.clevertec.logging.starter.aspect.LogMethodExecutionAspect0;
import ru.clevertec.logging.starter.aspect.LogMethodExecutionAspect1;
import ru.clevertec.logging.starter.config.constants.Constants;
import ru.clevertec.logging.starter.entity.AspectProperties;
import ru.clevertec.logging.starter.entity.LoggingServiceProperties;
import ru.clevertec.logging.starter.entity.MyPrototype;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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

//        AspectProperties aspectProperties0 = defaultLoggingServiceProperties.getAspectsProperties().get(0);
//        AspectProperties aspectProperties1 = defaultLoggingServiceProperties.getAspectsProperties().get(1);

        for (int i = 0; i < 2; i++) {
            String aspectClassName = "ru.clevertec.logging.starter.aspect" + ".LogMethodExecutionAspect" + i;
            AspectProperties aspectProperties = defaultLoggingServiceProperties.getAspectsProperties().get(i);
            LogMethodExecutionAspect aspectInstance = createAspectInstance(aspectClassName, aspectProperties);
            beanFactory.registerSingleton("LogMethodExecutionAspect" + i, aspectInstance);
        }

//        beanFactory.registerSingleton("LogMethodExecutionAspect0", new LogMethodExecutionAspect0(aspectProperties0));
//        beanFactory.registerSingleton("LogMethodExecutionAspect1", new LogMethodExecutionAspect1(aspectProperties1));


    }

    public LogMethodExecutionAspect createAspectInstance(String className, AspectProperties aspectProperties) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> c = Class.forName(className);
        Constructor<?> cons = c.getConstructor(AspectProperties.class);
        Object object = cons.newInstance(aspectProperties);
        return (LogMethodExecutionAspect) object;
    }

}

package ru.clevertec.logging.starter.config.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.logging.starter.aspect.LogMethodExecutionAspect;
import ru.clevertec.logging.starter.config.properties.PointcutProperties;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(name = "logging-service.enabled", havingValue = "true", matchIfMissing = true)
public class MultipleBeansApplicationContextInitializer implements BeanFactoryPostProcessor {

//    private PointcutProperties pointcutProperties;
//    private final PointcutProperties pointcutProperties;


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

//        pointcut.getConfigurablePointcuts().forEach(pointcut ->
//                beanFactory.registerSingleton("LogMethodExecutionAspect1", new LogMethodExecutionAspect(pointcut))
//        );

//        beanFactory.registerSingleton("pointcutProperties", new PointcutProperties());
//        System.out.println(pointcutProperties().getPointcuts());


        beanFactory.registerSingleton("LogMethodExecutionAspect1", new LogMethodExecutionAspect("1111111111111"));
        beanFactory.registerSingleton("LogMethodExecutionAspect2", new LogMethodExecutionAspect("2222222222222"));
    }

    @Bean
    public PointcutProperties pointcutProperties() {
        return new PointcutProperties();
    }
}
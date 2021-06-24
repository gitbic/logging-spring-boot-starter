package ru.clevertec.logging.starter.config.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.logging.starter.aspect.LogMethodExecutionAspect;

@Configuration
public class MultipleBeansApplicationContextInitializer implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.registerSingleton("LogMethodExecutionAspect1", new LogMethodExecutionAspect("1111111111111"));
        beanFactory.registerSingleton("LogMethodExecutionAspect2", new LogMethodExecutionAspect("2222222222222"));
    }
}
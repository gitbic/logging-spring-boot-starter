package ru.clevertec.logging.starter.config.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.logging.starter.entity.LoggingServiceProperties;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(name = "logging-service.enabled", havingValue = "true", matchIfMissing = true)
public class AspectInitializer implements BeanFactoryPostProcessor {

//    private final AdditionalMailProperties additionalMailProperties;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        LoggingServiceProperties loggingServiceProperties = beanFactory.getBean("loggingServiceProperties", LoggingServiceProperties.class);
        System.out.println(loggingServiceProperties);


//        ServerProperties serverProperties = beanFactory.getBean("serverProperties", ServerProperties.class);
//        System.out.println(serverProperties);

//        AdditionalMailProperties additionalMailPropertiesBean = beanFactory.getBean("additionalMailPropertiesBean", AdditionalMailProperties.class);
//        System.out.println(additionalMailPropertiesBean);

//        System.out.println(additionalMailProperties);

//        AdditionalMailProperties additionalMailProperties = beanFactory.getBean("additionalMailProperties", AdditionalMailProperties.class);
//        System.out.println(additionalMailProperties);
    }
}

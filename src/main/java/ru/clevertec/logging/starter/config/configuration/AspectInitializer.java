package ru.clevertec.logging.starter.config.configuration;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.logging.starter.entity.LoggingServiceProperties;
//import ru.clevertec.logging.starter.entity.ServerProperties;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(name = "logging-service.enabled", havingValue = "true", matchIfMissing = true)
public class AspectInitializer implements BeanFactoryPostProcessor {


    @SneakyThrows
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)  {
//        System.out.println(System.getProperty("user.dir"));

        LoggingServiceProperties loggingServiceProperties = beanFactory.getBean("loggingServiceProperties", LoggingServiceProperties.class);
        System.out.println(loggingServiceProperties);

        LoggingServiceProperties defaultLoggingServiceProperties = beanFactory.getBean("defaultLoggingServiceProperties", LoggingServiceProperties.class);
        System.out.println(defaultLoggingServiceProperties);


    }
}

package ru.clevertec.logging.starter.config.configuration;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import ru.clevertec.logging.starter.entity.LoggingServiceProperties;
import ru.clevertec.logging.starter.entity.ServerProperties;
//import ru.clevertec.logging.starter.entity.ServerProperties;

@Configuration
//@RequiredArgsConstructor
@ConditionalOnProperty(name = "logging-service.enabled", havingValue = "true", matchIfMissing = true)
public class AspectInitializer implements BeanFactoryPostProcessor, EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @SneakyThrows
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)  {



        LoggingServiceProperties defaultLoggingServiceProperties = beanFactory.getBean("defaultLoggingServiceProperties", LoggingServiceProperties.class);
        System.out.println(defaultLoggingServiceProperties);





        BindResult<LoggingServiceProperties> bindResult = Binder.get(environment)
                .bind("logging-service", LoggingServiceProperties.class);
        LoggingServiceProperties loggingServiceProperties = bindResult.get();
        System.out.println(loggingServiceProperties);


    }
}

package ru.clevertec.logging.starter.config.configuration;

import lombok.SneakyThrows;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import ru.clevertec.logging.starter.aspect.LogMethodExecutionAspect;
import ru.clevertec.logging.starter.config.constants.Constants;
import ru.clevertec.logging.starter.entity.AspectProperties;
import ru.clevertec.logging.starter.entity.LoggingServiceProperties;


@Configuration
//@RequiredArgsConstructor
@ConditionalOnProperty(name = Constants.LOGGING_SERVICE_ASPECT_ENABLED_PROPERTY, havingValue = "true", matchIfMissing = true)
public class AspectInitializer implements BeanFactoryPostProcessor, EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @SneakyThrows
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {

        LoggingServiceProperties defaultLoggingServiceProperties =
                beanFactory.getBean(Constants.DEFAULT_LOGGING_SERVICE_PROPERTIES_BEAN_NAME, LoggingServiceProperties.class);
        System.out.println(defaultLoggingServiceProperties);

        BindResult<LoggingServiceProperties> bindResult = Binder.get(environment)
                .bind(Constants.LOGGING_SERVICE_PROPERTY_NAME, LoggingServiceProperties.class);
        LoggingServiceProperties loggingServiceProperties = bindResult.get();
        System.out.println(loggingServiceProperties);

        int aspectBeanNumber = 1;
        for (AspectProperties aspectProperties : defaultLoggingServiceProperties.getAspectsProperties()) {
            if (!aspectProperties.isEnabled()) return;

            beanFactory.registerSingleton(LogMethodExecutionAspect.class.getSimpleName() + aspectBeanNumber++,
                    new LogMethodExecutionAspect(aspectProperties));
        }
//        System.out.println("=================");
//        beanFactory.registerSingleton(LogMethodExecutionAspect.class.getSimpleName() ,
//                new LogMethodExecutionAspect(loggingServiceProperties.getAspectsProperties().get(0)));





    }
}

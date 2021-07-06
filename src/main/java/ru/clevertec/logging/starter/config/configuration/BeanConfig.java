package ru.clevertec.logging.starter.config.configuration;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.logging.starter.aspect.LogMethodExecutionAspect0;
import ru.clevertec.logging.starter.aspect.LogMethodExecutionAspect1;
import ru.clevertec.logging.starter.config.constants.Constants;
import ru.clevertec.logging.starter.entity.AspectProperties;
import ru.clevertec.logging.starter.entity.LoggingServiceProperties;
import ru.clevertec.logging.starter.entity.MyPrototype;

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

        beanFactory.registerSingleton("LogMethodExecutionAspect0", new LogMethodExecutionAspect0(aspectProperties0));
        beanFactory.registerSingleton("LogMethodExecutionAspect1", new LogMethodExecutionAspect1(aspectProperties1));


    }
}

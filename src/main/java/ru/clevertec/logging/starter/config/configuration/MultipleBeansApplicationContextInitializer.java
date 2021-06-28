package ru.clevertec.logging.starter.config.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.logging.starter.aspect.LogMethodExecutionAspect;
import ru.clevertec.logging.starter.config.constants.Constants;
import ru.clevertec.logging.starter.entity.LoggingServiceProperties;

import java.io.File;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(name = "logging-service.enabled", havingValue = "true", matchIfMissing = true)
public class MultipleBeansApplicationContextInitializer implements BeanFactoryPostProcessor {

    @SneakyThrows
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        File file = new File(Constants.APPLICATION_YML_FILE_PATH); // todo файл может быть другим

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

        Map<String, Object> applicationYmlMap = objectMapper.readValue(file, new TypeReference<Map<String, Object>>() {
        });
        System.out.println(applicationYmlMap);

        Object loggingServicePropertiesMap = applicationYmlMap.get(Constants.LOGGING_SERVICE_PROPERTY_NAME);
        System.out.println(loggingServicePropertiesMap);

        LoggingServiceProperties loggingServiceProperties = objectMapper.convertValue(loggingServicePropertiesMap, LoggingServiceProperties.class);
        System.out.println(loggingServiceProperties);
//----------

        beanFactory.registerSingleton("LogMethodExecutionAspect1", new LogMethodExecutionAspect("1111111111111"));
        beanFactory.registerSingleton("LogMethodExecutionAspect2", new LogMethodExecutionAspect("2222222222222"));
    }
}
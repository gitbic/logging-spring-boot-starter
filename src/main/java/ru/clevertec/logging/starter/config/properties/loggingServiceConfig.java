package ru.clevertec.logging.starter.config.properties;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import ru.clevertec.logging.starter.config.constants.Constants;
import ru.clevertec.logging.starter.config.constants.PointcutPattern;
import ru.clevertec.logging.starter.entity.AspectProperties;
import ru.clevertec.logging.starter.entity.LoggingFormat;
import ru.clevertec.logging.starter.entity.LoggingServiceProperties;
import ru.clevertec.logging.starter.entity.ServerProperties;
import ru.clevertec.logging.starter.enums.ApiLayer;


import java.io.File;
import java.io.IOException;
import java.util.*;

@Configuration

public class loggingServiceConfig {

    @Bean
    public LoggingServiceProperties defaultLoggingServiceProperties()  {

        LoggingFormat controllerLoggingFormat = LoggingFormat.builder()
                .dateFormat(Constants.DEFAULT_DATE_TIME_FORMAT)
                .argumentPrints(true)
                .returnValuePrints(true)
                .build();

        AspectProperties controllerAspectProperties = AspectProperties.builder()
                .enabled(true)
                .layer(ApiLayer.CONTROLLER.getLayerName())
                .pattern(ApiLayer.CONTROLLER.getPointcutPattern())
                .loggingFormat(controllerLoggingFormat)
                .build();

        LoggingFormat serviceLoggingFormat = LoggingFormat.builder()
                .dateFormat(Constants.DEFAULT_DATE_TIME_FORMAT)
                .argumentPrints(true)
                .returnValuePrints(true)
                .build();

        AspectProperties serviceAspectProperties = AspectProperties.builder()
                .enabled(true)
                .layer(ApiLayer.SERVICE.getLayerName())
                .pattern(ApiLayer.SERVICE.getPointcutPattern())
                .loggingFormat(serviceLoggingFormat)
                .build();

        List<AspectProperties> aspectsProperties = new ArrayList<>();
        aspectsProperties.add(controllerAspectProperties);
        aspectsProperties.add(serviceAspectProperties);

        return new LoggingServiceProperties(true, aspectsProperties);
    }



//    @Bean
//    public LoggingServiceProperties loggingServiceProperties() throws IOException {
//        File file = new File(Constants.APPLICATION_YML_FILE_PATH); // todo файл может быть другим
//        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
//        Map<String, Object> applicationYmlMap = objectMapper.readValue(file, new TypeReference<Map<String, Object>>() {
//        });
//        Object loggingServicePropertiesMap = applicationYmlMap.get(Constants.LOGGING_SERVICE_PROPERTY_NAME);
//        LoggingServiceProperties loggingServiceProperties = objectMapper.convertValue(loggingServicePropertiesMap, LoggingServiceProperties.class);
//
//        System.out.println("file: " + file + " exist: " + file.exists());
////        System.out.println(applicationYmlMap);
////        System.out.println(loggingServicePropertiesMap);
////        System.out.println(loggingServiceProperties);
//
//        return loggingServiceProperties;
//    }


//    @Bean
//    public LoggingServiceProperties defaultLoggingServiceProperties() throws IOException {
//        File file = new File( "d:/repo/logging-aop-boot-starter/logging-spring-boot-starter/src/main/resources/application.yml"); // todo файл может быть другим
////        File file = new File( "src/main/resources/META-INF/application.yml"); // todo файл может быть другим
////        System.out.println("file: " + file + " exist: " + file.exists());
//
//        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
//
//        Map<String, Object> applicationYmlMap = objectMapper.readValue(file, new TypeReference<Map<String, Object>>() {
//        });
////        System.out.println(applicationYmlMap);
//
//        Object loggingServicePropertiesMap = applicationYmlMap.get(Constants.DEFAULT_LOGGING_SERVICE_PROPERTY_NAME);
////        System.out.println(loggingServicePropertiesMap);
//
//        LoggingServiceProperties loggingServiceProperties = objectMapper.convertValue(loggingServicePropertiesMap, LoggingServiceProperties.class);
////        System.out.println(loggingServiceProperties);
//
//        return loggingServiceProperties;
//    }


//    @Bean
//    @ConfigurationProperties("ds.client1")
//    public DataSource dataSourceClient1() {
//        DataSourceBuilder.create().build();
//    }


//    @Bean
//    public static BeanFactoryPostProcessor beanFactoryPostProcessor(
//            Environment environment) {
//        return new BeanFactoryPostProcessor() {
//
//            @Override
//            public void postProcessBeanFactory(
//                    ConfigurableListableBeanFactory beanFactory) throws BeansException {
//                BindResult<LoggingServiceProperties> result = Binder.get(environment)
//                        .bind("default-logging-service", LoggingServiceProperties.class);
//                LoggingServiceProperties loggingServiceProperties = result.get();
//                // Use the properties to post-process the bean factory as needed
//            }
//
//        };
//    }


}

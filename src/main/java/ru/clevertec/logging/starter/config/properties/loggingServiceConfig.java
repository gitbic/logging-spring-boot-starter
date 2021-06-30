package ru.clevertec.logging.starter.config.properties;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.logging.starter.config.constants.Constants;
import ru.clevertec.logging.starter.entity.LoggingServiceProperties;


import java.io.File;
import java.io.IOException;
import java.util.Map;

@Configuration
public class loggingServiceConfig {


//    @Bean(name = "loggingServiceProperties")
    @Bean
    public LoggingServiceProperties loggingServiceProperties() throws IOException {
        File file = new File(Constants.APPLICATION_YML_FILE_PATH); // todo файл может быть другим
        System.out.println("file: " + file + " exist: " + file.exists());

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

        Map<String, Object> applicationYmlMap = objectMapper.readValue(file, new TypeReference<Map<String, Object>>() {
        });
//        System.out.println(applicationYmlMap);

        Object loggingServicePropertiesMap = applicationYmlMap.get(Constants.LOGGING_SERVICE_PROPERTY_NAME);
//        System.out.println(loggingServicePropertiesMap);

        LoggingServiceProperties loggingServiceProperties = objectMapper.convertValue(loggingServicePropertiesMap, LoggingServiceProperties.class);
//        System.out.println(loggingServiceProperties);

        return loggingServiceProperties;
    }

    @Bean
    public LoggingServiceProperties defaultLoggingServiceProperties() throws IOException {
        File file = new File("d:/repo/logging-aop-boot-starter/logging-spring-boot-starter/src/main/resources/application.yml"); // todo файл может быть другим
        System.out.println("file: " + file + " exist: " + file.exists());

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

        Map<String, Object> applicationYmlMap = objectMapper.readValue(file, new TypeReference<Map<String, Object>>() {
        });
//        System.out.println(applicationYmlMap);

        Object loggingServicePropertiesMap = applicationYmlMap.get(Constants.DEFAULT_LOGGING_SERVICE_PROPERTY_NAME);
//        System.out.println(loggingServicePropertiesMap);

        LoggingServiceProperties loggingServiceProperties = objectMapper.convertValue(loggingServicePropertiesMap, LoggingServiceProperties.class);
//        System.out.println(loggingServiceProperties);

        return loggingServiceProperties;
    }


}

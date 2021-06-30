package ru.clevertec.logging.starter.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import ru.clevertec.logging.starter.config.constants.Constants;
import ru.clevertec.logging.starter.entity.LoggingServiceProperties;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ConfigurationReader {

    public static LoggingServiceProperties getLoggingServiceProperties(String loggingServicePropertyName) throws IOException {
        File file = new File(Constants.APPLICATION_YML_FILE_PATH); // todo файл может быть другим
//        System.out.println("file exists: " + file);

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

        Map<String, Object> applicationYmlMap = objectMapper.readValue(file, new TypeReference<Map<String, Object>>() {
        });
        System.out.println(applicationYmlMap);

        Object loggingServicePropertiesMap = applicationYmlMap.get(loggingServicePropertyName);
//        System.out.println(loggingServicePropertiesMap);

        LoggingServiceProperties loggingServiceProperties = objectMapper.convertValue(loggingServicePropertiesMap, LoggingServiceProperties.class);
//        System.out.println(loggingServiceProperties);

        return loggingServiceProperties;
    }
}

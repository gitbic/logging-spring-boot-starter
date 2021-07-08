package ru.clevertec.logging.starter.config.properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.logging.starter.config.constants.Constants;
import ru.clevertec.logging.starter.entity.AspectProperties;
import ru.clevertec.logging.starter.entity.LoggingFormat;
import ru.clevertec.logging.starter.entity.LoggingServiceProperties;
import ru.clevertec.logging.starter.enums.ApiLayer;


import java.util.*;

@Configuration
public class loggingServicePropertiesConfig {

    @Bean
    public LoggingServiceProperties defaultLoggingServiceProperties()  {

        LoggingFormat controllerLoggingFormat = LoggingFormat.builder()
                .dateFormat(Constants.DEFAULT_DATE_TIME_FORMAT)
                .argumentPrints(true)
                .returnValuePrints(true)
                .methodExecutingTimePrints(true)
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
                .returnValuePrints(false)
                .methodExecutingTimePrints(false)
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
}

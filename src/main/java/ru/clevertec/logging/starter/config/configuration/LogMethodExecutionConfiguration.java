package ru.clevertec.logging.starter.config.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.logging.starter.aspect.LogMethodExecutionAspect;
import ru.clevertec.logging.starter.config.properties.PointcutProperties;

@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(name = "logging-service.enabled", havingValue = "true", matchIfMissing = true)
public class LogMethodExecutionConfiguration {

    private final PointcutProperties pointcutProperties;

    @Bean
    public LogMethodExecutionAspect getLogMethodExecutionAspect(){
        return  new LogMethodExecutionAspect(pointcutProperties);
    }
}
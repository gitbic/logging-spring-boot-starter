package ru.clevertec.logging.starter.config.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.logging.starter.aspect.LogMethodExecutionAspect;

@Configuration
@ConditionalOnProperty(name = "logging-service.enabled", havingValue = "true", matchIfMissing = true)
public class LogMethodExecutionConfiguration {

    @Bean
    public LogMethodExecutionAspect getLogMethodExecutionAspect(){
        return  new LogMethodExecutionAspect();
    }
}
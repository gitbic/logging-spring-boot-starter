package ru.clevertec.starter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.starter.aspect.LogMethodExecutionAspect;

@Configuration
@ConditionalOnProperty(name = "logging-service.enabled", havingValue = "true", matchIfMissing = true)
public class LogMethodExecutionTimeAutoConfiguration {
    @Bean
    public LogMethodExecutionAspect getLogMethodExecutionAspect(){
        return  new LogMethodExecutionAspect();
    }
}
package ru.clevertec.logging.starter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
//@Component
//@ConfigurationProperties("default-logging-service")
//@AllArgsConstructor
public class LoggingServiceProperties {
    private boolean enabled;
    private List<AspectProperties> aspectsProperties;
}

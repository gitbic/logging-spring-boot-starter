package ru.clevertec.logging.starter.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
//@Component
//@ConfigurationProperties("default-logging-service")
public class LoggingServiceProperties {
    Boolean enabled;
    List<AspectProperties> aspectsProperties;
}

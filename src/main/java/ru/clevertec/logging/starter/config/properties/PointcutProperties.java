package ru.clevertec.logging.starter.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "logging-service")
public class PointcutProperties {
    private Map<String, String> pointcuts = new HashMap<>();
}

package ru.clevertec.logging.starter.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.logging.starter.config.entity.Pointcut;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "logging-service")
public class PointcutProperties {
    private List<Pointcut> pointcuts;
}

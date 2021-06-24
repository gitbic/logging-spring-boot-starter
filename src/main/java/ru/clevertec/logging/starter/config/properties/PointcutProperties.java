package ru.clevertec.logging.starter.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import ru.clevertec.logging.starter.entity.ConfigurablePointcut;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "logging-service")
public class PointcutProperties {
    private List<ConfigurablePointcut> pointcuts;
}

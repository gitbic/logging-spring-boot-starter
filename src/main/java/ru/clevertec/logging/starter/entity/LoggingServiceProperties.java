package ru.clevertec.logging.starter.entity;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LoggingServiceProperties {
    private boolean enabled;
    private List<AspectProperties> aspectsProperties;
}

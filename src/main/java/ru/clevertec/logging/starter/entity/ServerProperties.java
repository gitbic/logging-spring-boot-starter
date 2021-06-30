package ru.clevertec.logging.starter.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("server")
public class ServerProperties {
    private final String port;
}

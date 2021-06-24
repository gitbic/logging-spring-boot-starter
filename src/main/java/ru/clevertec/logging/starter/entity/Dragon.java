package ru.clevertec.logging.starter.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "dragon")
public class Dragon {
    String soldat;
    String worker;
}

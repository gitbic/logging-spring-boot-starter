package ru.clevertec.logging.starter.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
public class LoggingServiceProperties {
    Boolean enabled;
    List<AspectProperties> aspectsProperties;
}

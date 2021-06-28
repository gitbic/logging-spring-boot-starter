package ru.clevertec.logging.starter.entity;

import lombok.Data;

import java.util.List;

@Data
public class LoggingServiceProperties {
    Boolean enabled;
    List<ConfigurablePointcut> pointcuts;
}

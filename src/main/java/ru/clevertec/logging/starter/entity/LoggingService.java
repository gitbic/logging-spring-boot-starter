package ru.clevertec.logging.starter.entity;

import lombok.Data;

import java.util.List;

@Data
public class LoggingService {
    Boolean enabled;
    List<ConfigurablePointcut> pointcuts;
}

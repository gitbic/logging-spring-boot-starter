package ru.clevertec.logging.starter.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class AspectProperties {
    private List<String> patterns;
    private boolean enabled;
    private LoggingFormat loggingFormat;
}

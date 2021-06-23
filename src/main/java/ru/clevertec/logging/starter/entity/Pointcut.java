package ru.clevertec.logging.starter.entity;

import lombok.Data;

@Data
public class Pointcut {
    private String pattern;
    private boolean enabled;
    private LoggingFormat loggingFormat;
}

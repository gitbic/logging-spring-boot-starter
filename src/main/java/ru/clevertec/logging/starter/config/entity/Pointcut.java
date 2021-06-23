package ru.clevertec.logging.starter.config.entity;

import lombok.Data;

@Data
public class Pointcut {
    private String pattern;
    private boolean enabled;
    private LoggingFormat loggingFormat;
//    private String dateFormat;

}

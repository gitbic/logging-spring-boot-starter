package ru.clevertec.logging.starter.config.entity;

import lombok.Data;

@Data
public class LoggingFormat {
    private String dateFormat;
    private boolean printArguments;
}

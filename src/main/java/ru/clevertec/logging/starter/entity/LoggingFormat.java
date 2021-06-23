package ru.clevertec.logging.starter.entity;

import lombok.Data;

@Data
public class LoggingFormat {
    private String dateFormat;
    private boolean printArguments;
}

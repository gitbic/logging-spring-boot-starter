package ru.clevertec.logging.starter.entity;

import lombok.Builder;
import lombok.Data;

@Data
public class LoggingFormat {
    private String dateFormat;
    private boolean argumentPrints;
    private boolean returnValuePrints;
}

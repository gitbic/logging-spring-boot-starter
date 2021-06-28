package ru.clevertec.logging.starter.entity;

import lombok.Data;

@Data
public class ConfigurableLog {
    private String dateFormat;
    private boolean argumentPrints;
    private boolean returnValuePrints;
}

package ru.clevertec.logging.starter.entity;

import lombok.Data;

import java.util.List;

@Data
public class ConfigurablePointcut {
    private List<String> patterns;
    private boolean enabled;
    private ConfigurableLog loggingFormat;
}

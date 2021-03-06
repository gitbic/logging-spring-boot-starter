package ru.clevertec.logging.starter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AspectProperties {
    private String pattern;
    private String layer;
    private boolean enabled;
    private LoggingFormat loggingFormat;
}

package ru.clevertec.logging.starter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LoggingFormat {
    private String dateFormat;
    private boolean argumentPrints;
    private boolean returnValuePrints;
    private boolean methodExecutingTimePrints;
}

package ru.clevertec.logging.starter.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
public class Dragon {
    boolean enabled;
    String warriorKing;
    String worker;
    List<String> warriors;
}

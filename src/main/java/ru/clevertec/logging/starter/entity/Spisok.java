package ru.clevertec.logging.starter.entity;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
public class Spisok {
    List<String> stringList;
}

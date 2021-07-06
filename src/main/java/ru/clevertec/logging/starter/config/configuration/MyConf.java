package ru.clevertec.logging.starter.config.configuration;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.clevertec.logging.starter.entity.MyPrototype;

@Configuration
public class MyConf {
    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public MyPrototype createPrototype(String arg) {
        return new MyPrototype(arg);
    }
}

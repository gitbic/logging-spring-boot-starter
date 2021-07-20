package ru.clevertec.logging.starter.aspect;

import org.aspectj.lang.annotation.Pointcut;

import java.lang.annotation.Annotation;

public class DynamicPointCut implements Pointcut {
    private String defaultValue;

    public DynamicPointCut(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public String value() {
        return defaultValue;
    }

    @Override
    public String argNames() {
        return defaultValue;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return DynamicPointCut.class;
    }
}

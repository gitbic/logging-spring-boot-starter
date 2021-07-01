package ru.clevertec.logging.starter.enums;

import ru.clevertec.logging.starter.config.constants.PointcutPattern;

public enum ApiLayer {
    CONTROLLER(PointcutPattern.CONTROLLER_POINTCUT),
    SERVICE(PointcutPattern.SERVICE_POINTCUT),
    REPOSITORY(PointcutPattern.REPOSITORY_POINTCUT),
    ALL(PointcutPattern.ALL_DIRECTORY_POINTCUT);

    private String pointcutPattern;

    ApiLayer(String pointcutPattern) {
        this.pointcutPattern = pointcutPattern;
    }

    public String getPointcutPattern() {
        return pointcutPattern;
    }

    public String getLayerName() {
        return this.toString().toLowerCase();
    }
}

package ru.clevertec.logging.starter.enums;

import ru.clevertec.logging.starter.config.constants.PointcutPattern;

public enum ApiLayer {
    CONTROLLER(PointcutPattern.DEFAULT_CONTROLLER_POINTCUT),
    SERVICE(PointcutPattern.DEFAULT_SERVICE_POINTCUT),
    REPOSITORY(PointcutPattern.DEFAULT_REPOSITORY_POINTCUT),
    ALL(PointcutPattern.DEFAULT_ALL_DIRECTORY_POINTCUT);

    private final String pointcutPattern;

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

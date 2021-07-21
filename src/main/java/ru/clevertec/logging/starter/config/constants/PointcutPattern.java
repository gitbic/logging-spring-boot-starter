package ru.clevertec.logging.starter.config.constants;

public class PointcutPattern {
    public static final String DEFAULT_POINTCUT_PATTERN = "execution(* ru.clevertec..*repository*..*(..))";
    public static final String CONTROLLER_POINTCUT = "execution(* ru.clevertec..*controller*..*(..))";
    public static final String SERVICE_POINTCUT = "execution(* ru.clevertec..*service*..*(..))";
    public static final String REPOSITORY_POINTCUT = "execution(* ru.clevertec..*repository*..*(..))";
    public static final String LOGGING_STARTER_EXCLUDE_POINTCUT = "!execution(* ru.clevertec.logging.starter..*(..))";
    public static final String ALL_DIRECTORY_POINTCUT = "execution(* ru.clevertec..*(..))";
    public static final String POINTCUT_AND_DELIMITER = " && ";
    public static final String POINTCUT_OR_DELIMITER = " || ";

    public static final String DEFAULT_THREE_POINTCUT = CONTROLLER_POINTCUT +
            POINTCUT_OR_DELIMITER + SERVICE_POINTCUT +
            POINTCUT_OR_DELIMITER + REPOSITORY_POINTCUT +
            POINTCUT_AND_DELIMITER + LOGGING_STARTER_EXCLUDE_POINTCUT;

    public static final String DEFAULT_ALL_POINTCUT = ALL_DIRECTORY_POINTCUT +
            POINTCUT_AND_DELIMITER + LOGGING_STARTER_EXCLUDE_POINTCUT;

    public static final String DEFAULT_CONTROLLER_POINTCUT = CONTROLLER_POINTCUT +
            POINTCUT_AND_DELIMITER + LOGGING_STARTER_EXCLUDE_POINTCUT;

    public static final String DEFAULT_SERVICE_POINTCUT = SERVICE_POINTCUT +
            POINTCUT_AND_DELIMITER + LOGGING_STARTER_EXCLUDE_POINTCUT;

    public static final String DEFAULT_REPOSITORY_POINTCUT = REPOSITORY_POINTCUT +
            POINTCUT_AND_DELIMITER + LOGGING_STARTER_EXCLUDE_POINTCUT;

    public static final String DEFAULT_ALL_DIRECTORY_POINTCUT = ALL_DIRECTORY_POINTCUT +
            POINTCUT_AND_DELIMITER + LOGGING_STARTER_EXCLUDE_POINTCUT;
}

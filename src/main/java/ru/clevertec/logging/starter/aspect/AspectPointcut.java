package ru.clevertec.logging.starter.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import ru.clevertec.logging.starter.config.constants.PointcutPattern;

@Aspect
public class AspectPointcut {

    @Pointcut(PointcutPattern.CONTROLLER_POINTCUT)
    public void getControllerPointcut(){}

    @Pointcut(PointcutPattern.SERVICE_POINTCUT)
    public void getServicePointcut(){}

    @Pointcut(PointcutPattern.REPOSITORY_POINTCUT)
    public void getRepositoryPointcut() {}

}

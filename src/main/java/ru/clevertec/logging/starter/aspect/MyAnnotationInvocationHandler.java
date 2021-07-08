package ru.clevertec.logging.starter.aspect;

import sun.reflect.annotation.ExceptionProxy;

import java.lang.annotation.Annotation;
import java.lang.annotation.AnnotationFormatError;
import java.lang.annotation.IncompleteAnnotationException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

class MyAnnotationInvocationHandler implements InvocationHandler{
    private final Class<? extends Annotation> type;
    private final Map<String, Object> memberValues;

    MyAnnotationInvocationHandler(Class<? extends Annotation> type, Map<String, Object> memberValues) {
        Class<?>[] superInterfaces = type.getInterfaces();
        if (!type.isAnnotation() ||
                superInterfaces.length != 1 ||
                superInterfaces[0] != java.lang.annotation.Annotation.class)
            throw new AnnotationFormatError("Attempt to create proxy for a non-annotation type.");
        this.type = type;
        this.memberValues = memberValues;
    }

    public Object invoke(Object proxy, Method method, Object[] args) {
        String member = method.getName();
        Class<?>[] paramTypes = method.getParameterTypes();


        // Handle annotation member accessors

        return memberValues.get(member);
    }

}
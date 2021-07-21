package ru.clevertec.logging.starter.config.configuration;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonBuilderUtils;
import ru.clevertec.logging.starter.aspect.LogMethodExecutionAspect0;
import ru.clevertec.logging.starter.aspect.LogMethodExecutionAspect1;
import ru.clevertec.logging.starter.config.constants.Constants;
import ru.clevertec.logging.starter.config.constants.PointcutPattern;
import ru.clevertec.logging.starter.entity.AspectProperties;
import ru.clevertec.logging.starter.entity.LoggingServiceProperties;
import ru.clevertec.logging.starter.entity.MyPrototype;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static ru.clevertec.logging.starter.config.constants.PointcutPattern.DEFAULT_POINTCUT_PATTERN;

@Configuration
public class BeanConfig implements BeanFactoryPostProcessor {

    private static final Unsafe unsafe;

    static
    {
        try
        {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe)field.get(null);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.registerSingleton("LKnvja", new ModelMapper());

        beanFactory.registerSingleton("myPrototype1", new MyPrototype("Hello from prototype1"));
        beanFactory.registerSingleton("myPrototype2", new MyPrototype("Hello from prototype2"));


        LoggingServiceProperties defaultLoggingServiceProperties =
                beanFactory.getBean(Constants.DEFAULT_LOGGING_SERVICE_PROPERTIES_BEAN_NAME, LoggingServiceProperties.class);
        System.out.println(defaultLoggingServiceProperties);

        AspectProperties aspectProperties0 = defaultLoggingServiceProperties.getAspectsProperties().get(0);
        AspectProperties aspectProperties1 = defaultLoggingServiceProperties.getAspectsProperties().get(1);


        changeConstant(PointcutPattern.CONTROLLER_POINTCUT);
        beanFactory.registerSingleton("LogMethodExecutionAspect0", new LogMethodExecutionAspect0(aspectProperties0));
        changeConstant(PointcutPattern.SERVICE_POINTCUT);
        beanFactory.registerSingleton("LogMethodExecutionAspect1", new LogMethodExecutionAspect0(aspectProperties1));


    }

    private void changeConstant(String targetValue) throws NoSuchFieldException, IllegalAccessException {
        System.out.println("DEFAULT_POINTCUT_PATTERN before changes: " + DEFAULT_POINTCUT_PATTERN);
        final Field fieldToUpdate = PointcutPattern.class.getDeclaredField("DEFAULT_POINTCUT_PATTERN");
        fieldToUpdate.setAccessible(true);

        //'modifiers' - it is a field of a class called 'Field'. Make it accessible and remove
        //'final' modifier for our 'CONSTANT' field
        Field modifiersField = Field.class.getDeclaredField( "modifiers" );
        modifiersField.setAccessible( true );
        modifiersField.setInt( fieldToUpdate, fieldToUpdate.getModifiers() & ~Modifier.FINAL );
        //it updates a field, but it was already inlined during compilation...
        fieldToUpdate.set( null, targetValue );


//        //this is a 'base'. Usually a Class object will be returned here.
//        final Object base = unsafe.staticFieldBase( fieldToUpdate );
//        //this is an 'offset'
//        final long offset = unsafe.staticFieldOffset( fieldToUpdate );
//        //actual update
//        unsafe.putObject( base, offset, targetValue );


        System.out.println("DEFAULT_POINTCUT_PATTERN after changes: " + DEFAULT_POINTCUT_PATTERN);
        System.out.println("DEFAULT_POINTCUT_PATTERN after changes REAL: " + fieldToUpdate.get(null));

    }
}

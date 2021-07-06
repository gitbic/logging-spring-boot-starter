package ru.clevertec.logging.starter.config;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.logging.starter.entity.MyPrototype;

@Configuration

public class BeanConfig implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        beanFactory.registerSingleton("LKnvja", new ModelMapper());

        beanFactory.registerSingleton("myPrototype1", new MyPrototype("Hello"));
        beanFactory.registerSingleton("myPrototype2", new MyPrototype("Bye"));

        //	private final ObjectProvider<MyPrototype> myPrototypeProvider;

        ObjectProvider<MyPrototype> myPrototypeProvider = beanFactory.getBeanProvider(MyPrototype.class);

//        final MyPrototype myPrototype = myPrototypeProvider.getObject("hello");
//
//        beanFactory.registerSingleton("myPrototype", myPrototype);

    }
}

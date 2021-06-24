package ru.clevertec.logging.starter.config.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import ru.clevertec.logging.starter.aspect.LogMethodExecutionAspect;
import ru.clevertec.logging.starter.entity.Dragon;
import ru.clevertec.logging.starter.entity.Spisok;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(name = "logging-service.enabled", havingValue = "true", matchIfMissing = true)
public class MultipleBeansApplicationContextInitializer implements BeanFactoryPostProcessor, EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

//        PointcutProperties pointcutProperties = environment.getProperty("logging-service", PointcutProperties.class);
//        System.out.println(pointcutProperties);



        Spisok spisok = environment.getProperty("logging-service.roles", Spisok.class);
        System.out.println(spisok);

        List<String> stringList = environment.getProperty("logging-service.pointcuts", List.class);
        System.out.println(stringList);

        String dragonString = environment.getProperty("dragon.worker", String.class);
        System.out.println(dragonString);

        Dragon dragon = environment.getProperty("dragon", Dragon.class);
        System.out.println(dragon);

        String product = environment.getProperty("logging-service.product", String.class);
        System.out.println(product);

        Boolean enabled = environment.getProperty("logging-service.enabled", Boolean.class);
        System.out.println(enabled);

        beanFactory.registerSingleton("LogMethodExecutionAspect1", new LogMethodExecutionAspect("1111111111111"));
        beanFactory.registerSingleton("LogMethodExecutionAspect2", new LogMethodExecutionAspect("2222222222222"));
    }


//
//    @Bean
//    public Dragon dragon() {
//        return new Dragon();
//    }

//    @Bean
//    public PointcutProperties pointcutProperties(Environment environment) {
//        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
//        yaml.setResources(new FileSystemResource(environment.resolvePlaceholders("application.yml")));
//        yaml.setResolutionMethod(YamlProcessor.ResolutionMethod.OVERRIDE);
//        yaml.afterPropertiesSet();
//        Map<Object, Object> yamlProperties = yaml.getObject();
//        System.out.println(yamlProperties);
//
//
//        return new PointcutProperties();
//    }

//    @Bean
//    public static BeanFactoryPostProcessor beanFactoryPostProcessor(Environment environment) {
//        return new BeanFactoryPostProcessor() {
//
//            @Override
//            public void postProcessBeanFactory(
//                    ConfigurableListableBeanFactory beanFactory) throws BeansException {
//                BindResult<PointcutProperties> result = Binder.get(environment)
//                        .bind("ru.clevertec.logging.starter.config.properties", PointcutProperties.class);
//                PointcutProperties properties = result.get();
//                // Use the properties to post-process the bean factory as needed
//            }
//
//        };
//    }

}
package ru.clevertec.logging.starter.config.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import ru.clevertec.logging.starter.aspect.LogMethodExecutionAspect;
import ru.clevertec.logging.starter.entity.Dragon;
import ru.clevertec.logging.starter.entity.LoggingService;

import java.io.File;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(name = "logging-service.enabled", havingValue = "true", matchIfMissing = true)
public class MultipleBeansApplicationContextInitializer implements BeanFactoryPostProcessor, EnvironmentAware {


    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        File file = new File("src/main/resources/application.yml");
        System.out.println("file exist: " + file.exists());


//        YAMLFactory yamlFactory = new YAMLFactory();
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

        Map<String, Object> applicationYmlMap = objectMapper.readValue(file, new TypeReference<Map<String, Object>>() {
        });
        System.out.println(applicationYmlMap);
//----------
        Object dragonProperties = applicationYmlMap.get("dragon");
        System.out.println(dragonProperties);

        Dragon dragon = objectMapper.convertValue(dragonProperties, Dragon.class);
        System.out.println(dragon);
//----------
        Object warriors = applicationYmlMap.get("dragon.warriors");
        System.out.println(dragonProperties);

        List<String> wars = objectMapper.convertValue(warriors, List.class);
        System.out.println(wars);
//----------
        Object buhloMap = applicationYmlMap.get("buhlo");
        System.out.println(buhloMap);

        List<String> buhlo = objectMapper.convertValue(buhloMap, List.class);
        System.out.println(buhlo);
//----------
        Object loggingServicePropertiesMap = applicationYmlMap.get("loggingService");
        System.out.println(loggingServicePropertiesMap);

        LoggingService loggingService = objectMapper.convertValue(loggingServicePropertiesMap, LoggingService.class);
        System.out.println(loggingService);
//----------


        //-----------------------------------------------
//
//        Spisok spisok = environment.getProperty("logging-service.roles", Spisok.class);
//        System.out.println(spisok);
//
//        List<String> stringList = environment.getProperty("logging-service.pointcuts", List.class);
//        System.out.println(stringList);
//
//        String dragonString = environment.getProperty("dragon.worker", String.class);
//        System.out.println(dragonString);
//
//        Object dragon = environment.getProperty("dragon", Object.class);
//        System.out.println(dragon);
//
//        String product = environment.getProperty("logging-service.product", String.class);
//        System.out.println(product);
//
//        Boolean enabled = environment.getProperty("logging-service.enabled", Boolean.class);
//        System.out.println(enabled);
//-----------------------------------------------
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
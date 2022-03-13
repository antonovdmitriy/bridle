package com.bridle.core.configuration;

import com.bridle.core.components.http.HttpOutFactory;
import com.bridle.core.components.http.SimpleHttpOutEndpointProducerBuilderFactory;
import com.bridle.core.components.kafka.KafkaOutFactory;
import com.bridle.core.components.kafka.SimpleKafkaOutEndpointProducerBuilderFactory;
import com.bridle.core.components.sheduler.SchedulerFactory;
import com.bridle.core.components.sheduler.SimpleSchedulerEndpointConsumerBuilderFactory;
import com.bridle.core.properties.DefaultPropertiesLoaderWIthJsr303Validation;
import com.bridle.core.properties.PropertiesLoader;
import com.bridle.core.properties.validation.ConfigurationPropertiesJsr303Validator;
import org.springframework.boot.context.properties.bind.BindHandler;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.bind.validation.ValidationBindHandler;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleBridleConfiguration {

    private final ApplicationContext context;

    public SimpleBridleConfiguration(ApplicationContext context) {
        this.context = context;
    }

    @Bean
    PropertiesLoader propertiesLoaderService() {
        Binder configurationBinder = new Binder(
                ConfigurationPropertySources.get(context.getEnvironment()));
        BindHandler validationHandler = new ValidationBindHandler(
                new ConfigurationPropertiesJsr303Validator(context));
        return new DefaultPropertiesLoaderWIthJsr303Validation(configurationBinder,
                validationHandler);
    }

    @Bean
    SchedulerFactory schedulerFactory(PropertiesLoader propertiesLoaderService) {
        return new SimpleSchedulerEndpointConsumerBuilderFactory(propertiesLoaderService);
    }

    @Bean
    HttpOutFactory httpOutFactory(PropertiesLoader propertiesLoaderService) {
        return new SimpleHttpOutEndpointProducerBuilderFactory(propertiesLoaderService);
    }

    @Bean
    KafkaOutFactory kafkaOutFactory(PropertiesLoader propertiesLoaderService) {
        return new SimpleKafkaOutEndpointProducerBuilderFactory(propertiesLoaderService);
    }
}

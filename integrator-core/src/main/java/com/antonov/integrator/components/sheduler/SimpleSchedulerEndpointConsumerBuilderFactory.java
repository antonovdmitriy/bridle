package com.antonov.integrator.components.sheduler;

import com.antonov.integrator.components.EndpointConsumerBuilderFactory;
import org.apache.camel.builder.EndpointConsumerBuilder;
import org.springframework.core.env.Environment;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.scheduler;


public class SimpleSchedulerEndpointConsumerBuilderFactory implements EndpointConsumerBuilderFactory {

    private Environment environment;
    //TODO Сделать загрузку пропертей через конфигурацию

    public SimpleSchedulerEndpointConsumerBuilderFactory(Environment environment) {
        this.environment = environment;
    }

    @Override
    public EndpointConsumerBuilder create(String componentName) {
        return scheduler("")
                .delay(environment.getRequiredProperty(componentName + "." + "delay-millis", Long.class))
                .poolSize(environment.getRequiredProperty(componentName + "." + "thread-count", Integer.class));
    }
}

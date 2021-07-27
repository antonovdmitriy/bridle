package com.antonov.components;

import org.apache.camel.builder.EndpointConsumerBuilder;
import org.springframework.core.env.Environment;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.scheduler;


public class SchedulerFactory {

    private Environment environment;

    public SchedulerFactory(Environment environment) {
        this.environment = environment;
    }

    public EndpointConsumerBuilder createScheduler(String componentName) {
        return scheduler("")
                .delay(environment.getRequiredProperty(componentName + "." + "delay-millis", Long.class))
                .poolSize(environment.getRequiredProperty(componentName + "." + "thread-count", Integer.class));
    }
}

package com.antonov.integrator.components.sheduler;

import org.apache.camel.builder.EndpointConsumerBuilder;
import org.springframework.core.env.Environment;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.scheduler;


public class SimpleSchedulerBuilder implements SchedulerBuilder {

    private Environment environment;

    public SimpleSchedulerBuilder(Environment environment) {
        this.environment = environment;
    }

    @Override
    public EndpointConsumerBuilder createScheduler(String componentName) {
        return scheduler("")
                .delay(environment.getRequiredProperty(componentName + "." + "delay-millis", Long.class))
                .poolSize(environment.getRequiredProperty(componentName + "." + "thread-count", Integer.class));
    }
}

package com.bridle.core.components.sheduler;

import com.bridle.core.components.EndpointConsumerBuilderFactory;
import com.bridle.core.properties.PropertiesLoader;
import org.apache.camel.builder.EndpointConsumerBuilder;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.scheduler;


public class SimpleSchedulerEndpointConsumerBuilderFactory implements EndpointConsumerBuilderFactory {

    public static final String DEFAULT_CONFIG_PROPERTIES_KEY_SCHEDULER = "scheduler";
    private PropertiesLoader propertiesLoader;

    public SimpleSchedulerEndpointConsumerBuilderFactory(PropertiesLoader propertiesLoader) {
        this.propertiesLoader = propertiesLoader;
    }

    @Override
    public EndpointConsumerBuilder create(String componentName) {
        SchedulerProperties properties = propertiesLoader.load(SchedulerProperties.class, componentName);
        return scheduler("")
                .delay(properties.getDelayMillis())
                .poolSize(properties.getThreadCount());
    }

    @Override
    public EndpointConsumerBuilder create() {
        return create(DEFAULT_CONFIG_PROPERTIES_KEY_SCHEDULER);
    }
}

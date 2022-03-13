package com.bridle.core.components.sheduler;

import com.bridle.core.properties.PropertiesLoader;
import org.apache.camel.builder.EndpointConsumerBuilder;
import org.apache.camel.builder.EndpointProducerBuilder;

import java.util.Map;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.scheduler;


public class SimpleSchedulerEndpointConsumerBuilderFactory implements SchedulerFactory {

    public static final String DEFAULT_CONFIG_PROPERTIES_KEY_SCHEDULER = "scheduler";
    private final PropertiesLoader propertiesLoader;

    public SimpleSchedulerEndpointConsumerBuilderFactory(PropertiesLoader propertiesLoader) {
        this.propertiesLoader = propertiesLoader;
    }

    @Override
    public EndpointConsumerBuilder create(String componentName) {
        SchedulerProperties properties = propertiesLoader.load(SchedulerProperties.class, componentName);
        EndpointConsumerBuilder component = scheduler("")
                .delay(properties.getDelayMillis())
                .poolSize(properties.getThreadCount());
        Map<String, Object> additionalProperties = properties.getAdditional();
        if (additionalProperties != null && !additionalProperties.isEmpty()) {
            additionalProperties.forEach(component::doSetProperty);
        }
        return component;
    }

    @Override
    public EndpointConsumerBuilder create() {
        return create(DEFAULT_CONFIG_PROPERTIES_KEY_SCHEDULER);
    }
}

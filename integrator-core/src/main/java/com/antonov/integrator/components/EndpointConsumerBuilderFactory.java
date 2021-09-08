package com.antonov.integrator.components;

import org.apache.camel.builder.EndpointConsumerBuilder;

public interface EndpointConsumerBuilderFactory {
    EndpointConsumerBuilder create(String componentName);
}

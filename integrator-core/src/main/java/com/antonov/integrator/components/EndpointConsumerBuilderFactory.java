package com.antonov.integrator.components;

import org.apache.camel.builder.EndpointConsumerBuilder;
import org.apache.camel.builder.EndpointProducerBuilder;

public interface EndpointConsumerBuilderFactory {
    EndpointConsumerBuilder create(String componentName);

    EndpointConsumerBuilder create();
}

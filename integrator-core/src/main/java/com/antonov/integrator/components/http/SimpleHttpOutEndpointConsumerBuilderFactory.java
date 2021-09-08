package com.antonov.integrator.components.http;

import com.antonov.integrator.components.EndpointProducerBuilderFactory;
import org.apache.camel.builder.EndpointProducerBuilder;
import org.springframework.core.env.Environment;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.http;
import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.scheduler;

public class SimpleHttpOutEndpointConsumerBuilderFactory implements EndpointProducerBuilderFactory {

    private Environment environment;
    //TODO Сделать загрузку пропертей через конфигурацию

    public SimpleHttpOutEndpointConsumerBuilderFactory(Environment environment) {
        this.environment = environment;
    }

    @Override
    public EndpointProducerBuilder create(String componentName) {
        return http(environment.getRequiredProperty(componentName + "." + "url", String.class));
    }
}

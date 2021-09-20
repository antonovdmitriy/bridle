package com.bridle.core.components.http;

import com.bridle.core.components.EndpointProducerBuilderFactory;
import com.bridle.core.properties.PropertiesLoader;
import org.apache.camel.builder.EndpointProducerBuilder;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;

import java.util.Map;
import java.util.function.Consumer;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.http;

public class SimpleHttpOutEndpointConsumerBuilderFactory implements EndpointProducerBuilderFactory {

    public static final String DEFAULT_CONFIG_PROPERTIES_KEY_HTTP_OUT = "http-out";
    private PropertiesLoader loader;

    public SimpleHttpOutEndpointConsumerBuilderFactory(PropertiesLoader loader) {
        this.loader = loader;
    }

    @Override
    public EndpointProducerBuilder create(String componentName) {
        HttpOutProperties properties = loader.load(HttpOutProperties.class, componentName);
        EndpointProducerBuilder component =  http(properties.getUrl());
        properties.getAdditional().forEach(component::doSetProperty);
        return component;
    }

    @Override
    public EndpointProducerBuilder create() {
        return create(DEFAULT_CONFIG_PROPERTIES_KEY_HTTP_OUT);
    }
}

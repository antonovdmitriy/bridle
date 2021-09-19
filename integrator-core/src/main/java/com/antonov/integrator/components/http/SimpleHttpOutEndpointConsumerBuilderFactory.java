package com.antonov.integrator.components.http;

import com.antonov.integrator.components.EndpointProducerBuilderFactory;
import com.antonov.integrator.validation.ConfigurationPropertiesJsr303Validator;
import org.apache.camel.builder.EndpointProducerBuilder;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.bind.validation.ValidationBindHandler;
import org.springframework.context.ApplicationContext;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.http;

public class SimpleHttpOutEndpointConsumerBuilderFactory implements EndpointProducerBuilderFactory {

    public static final String DEFAULT_CONFIG_PROPERTIES_KEY_HTTP_OUT = "http-out";
    private Binder configurationBinder;
    private ApplicationContext context;

    public SimpleHttpOutEndpointConsumerBuilderFactory(Binder configurationBinder, ApplicationContext context) {
        this.configurationBinder = configurationBinder;
        this.context = context;
    }

    @Override
    public EndpointProducerBuilder create(String componentName) {
        HttpOutProperties properties = configurationBinder.
                bind(componentName, Bindable.of(HttpOutProperties.class),
                        new ValidationBindHandler(
                                new ConfigurationPropertiesJsr303Validator(context))).get();
        return http(properties.getUrl());
    }

    @Override
    public EndpointProducerBuilder create() {
        return create(DEFAULT_CONFIG_PROPERTIES_KEY_HTTP_OUT);
    }
}

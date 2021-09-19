package com.antonov.integrator.components.sheduler;

import com.antonov.integrator.components.EndpointConsumerBuilderFactory;
import com.antonov.integrator.validation.ConfigurationPropertiesJsr303Validator;
import org.apache.camel.builder.EndpointConsumerBuilder;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.bind.validation.ValidationBindHandler;
import org.springframework.context.ApplicationContext;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.scheduler;


public class SimpleSchedulerEndpointConsumerBuilderFactory implements EndpointConsumerBuilderFactory {

    public static final String DEFAULT_CONFIG_PROPERTIES_KEY_SCHEDULER = "scheduler";
    private Binder configurationBinder;
    private ApplicationContext context;

    public SimpleSchedulerEndpointConsumerBuilderFactory(Binder configurationBinder, ApplicationContext context) {
        this.configurationBinder = configurationBinder;
        this.context = context;
    }

    @Override
    public EndpointConsumerBuilder create(String componentName) {
        SchedulerProperties properties = configurationBinder.
                bind(componentName, Bindable.of(SchedulerProperties.class),
                        new ValidationBindHandler(
                                new ConfigurationPropertiesJsr303Validator(context))).get();
        return scheduler("")
                .delay(properties.getDelayMillis())
                .poolSize(properties.getThreadCount());
    }

    @Override
    public EndpointConsumerBuilder create() {
        return create(DEFAULT_CONFIG_PROPERTIES_KEY_SCHEDULER);
    }
}

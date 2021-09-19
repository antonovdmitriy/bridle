package com.antonov.integrator.configuration;

import com.antonov.integrator.components.EndpointConsumerBuilderFactory;
import com.antonov.integrator.components.EndpointProducerBuilderFactory;
import com.antonov.integrator.components.http.SimpleHttpOutEndpointConsumerBuilderFactory;
import com.antonov.integrator.components.sheduler.SchedulerProperties;
import com.antonov.integrator.components.sheduler.SimpleSchedulerEndpointConsumerBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;

@Configuration
public class IntegratorConfiguration {

    private Binder configurationBinder;
    @Autowired
    private ApplicationContext context;
    private Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        configurationBinder = new Binder(ConfigurationPropertySources.get(environment));
        this.environment = environment;
    }

    @Bean
    EndpointConsumerBuilderFactory schedulerFactory() {
        return new SimpleSchedulerEndpointConsumerBuilderFactory(configurationBinder, context);
    }

    @Bean
    EndpointProducerBuilderFactory httpOutFactory() {
        return new SimpleHttpOutEndpointConsumerBuilderFactory(configurationBinder, context);
    }
}

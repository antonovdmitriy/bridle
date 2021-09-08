package com.antonov.integrator.configuration;

import com.antonov.integrator.components.EndpointConsumerBuilderFactory;
import com.antonov.integrator.components.EndpointProducerBuilderFactory;
import com.antonov.integrator.components.http.SimpleHttpOutEndpointConsumerBuilderFactory;
import com.antonov.integrator.components.sheduler.SimpleSchedulerEndpointConsumerBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class IntegratorConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    EndpointConsumerBuilderFactory schedulerFactory() {
        return new SimpleSchedulerEndpointConsumerBuilderFactory(environment);
    }

    @Bean
    EndpointProducerBuilderFactory httpOutFactory() {
        return new SimpleHttpOutEndpointConsumerBuilderFactory(environment);
    }
}

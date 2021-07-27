package com.antonov.integrator.configuration;

import com.antonov.integrator.components.sheduler.SchedulerBuilder;
import com.antonov.integrator.components.sheduler.SimpleSchedulerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class IntegratorConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    SchedulerBuilder schedulerBuilder() {
        return new SimpleSchedulerBuilder(environment);
    }
}

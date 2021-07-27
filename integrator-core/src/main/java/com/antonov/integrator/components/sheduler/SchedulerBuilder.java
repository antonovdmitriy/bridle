package com.antonov.integrator.components.sheduler;

import org.apache.camel.builder.EndpointConsumerBuilder;

public interface SchedulerBuilder {
    EndpointConsumerBuilder createScheduler(String componentName);
}

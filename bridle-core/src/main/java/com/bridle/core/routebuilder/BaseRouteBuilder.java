package com.bridle.core.routebuilder;

import com.bridle.core.components.EndpointConsumerBuilderFactory;
import com.bridle.core.components.EndpointProducerBuilderFactory;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseRouteBuilder extends RouteBuilder {
    @Autowired
    protected EndpointConsumerBuilderFactory schedulerFactory;
    @Autowired
    protected EndpointProducerBuilderFactory httpOutFactory;
}

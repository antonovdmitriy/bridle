package com.antonov.quake.reader;

import com.antonov.integrator.components.EndpointConsumerBuilderFactory;
import com.antonov.integrator.components.EndpointProducerBuilderFactory;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReaderRoute extends RouteBuilder {

    @Autowired
    private EndpointConsumerBuilderFactory schedulerFactory;
    @Autowired
    private EndpointProducerBuilderFactory httpOutFactory;

    @Override
    public void configure() {
        from(schedulerFactory.create("scheduler1"))
                .to(httpOutFactory.create("httpOut"))
//                .process(exchange -> exchange.getIn().setBody("first"))
                .convertBodyTo(String.class)
////                .split(body().tokenize("\n"))
////                .removeHeaders("*")
////                .to("kafka:{{kafka-out.topic}}?brokers={{kafka-out.brokers}}")
////                .process(exchange -> exchange.setBody("first"))
                .to("log:log");
//        from(schedulerFactory.create("scheduler2"))
//                .process(exchange -> exchange.getIn().setBody("first"))
//                .to("log:log");
    }
}

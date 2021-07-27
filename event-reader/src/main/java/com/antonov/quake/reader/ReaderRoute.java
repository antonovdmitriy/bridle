package com.antonov.quake.reader;

import com.antonov.components.SchedulerFactory;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReaderRoute extends RouteBuilder {

    @Autowired
    private SchedulerFactory schedulerFactory;

    @Override
    public void configure() throws Exception {
        getScheduler1()
//                .convertBodyTo(String.class)
//                .split(body().tokenize("\n"))
//                .removeHeaders("*")
//                .to("kafka:{{kafka-out.topic}}?brokers={{kafka-out.brokers}}")
                .process(exchange -> exchange.setBody("first"))
                .to("log:log");

        from(schedulerFactory.createScheduler("scheduler2"))
                .process(exchange -> exchange.getIn().setBody("second"))
                .to("log:log");
    }

    public RouteDefinition getScheduler1() {
        return from(schedulerFactory.createScheduler("scheduler1"))
                .to("{{reader.url}}");
    }
}

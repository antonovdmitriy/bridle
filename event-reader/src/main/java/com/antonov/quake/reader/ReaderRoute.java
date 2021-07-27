package com.antonov.quake.reader;

import com.antonov.integrator.components.sheduler.SchedulerBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReaderRoute extends RouteBuilder {

    @Autowired
    private SchedulerBuilder schedulerBuilder;

    @Override
    public void configure() throws Exception {
        from(schedulerBuilder.createScheduler("scheduler1"))
                .process(exchange -> exchange.getIn().setBody("first"))
//                .convertBodyTo(String.class)
//                .split(body().tokenize("\n"))
//                .removeHeaders("*")
//                .to("kafka:{{kafka-out.topic}}?brokers={{kafka-out.brokers}}")
//                .process(exchange -> exchange.setBody("first"))
                .to("log:log");

        from(schedulerBuilder.createScheduler("scheduler2"))
                .process(exchange -> exchange.getIn().setBody("second"))
                .to("log:log");
    }

}

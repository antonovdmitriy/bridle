package com.antonov.quake.reader;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ReaderRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("scheduler:readTimer?delay={{reader.delay-millis}}&poolSize={{reader.thread-count}}")
                .to("{{reader.url}}")
                .convertBodyTo(String.class)
                .split(body().tokenize("\n"))
                .removeHeaders("*")
                .unmarshal("json")
                .to("kafka:{{kafka-out.topic}}?brokers={{kafka-out.brokers}}")
                .to("log:log");
    }
}

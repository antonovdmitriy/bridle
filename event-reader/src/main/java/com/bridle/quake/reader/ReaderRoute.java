package com.bridle.quake.reader;

import com.bridle.core.routebuilder.BaseRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ReaderRoute extends BaseRouteBuilder {

    @Override
    public void configure() {
        from(schedulerFactory.create()).routeId("quakeEventReader")
                .to(httpOutFactory.create())
                .convertBodyTo(String.class)
                .to("log:log");
    }
}

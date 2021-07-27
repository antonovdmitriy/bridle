package com.antonov.quake.reader;

import com.antonov.components.SchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@SpringBootApplication()
@Configuration
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private Environment environment;

    @Bean
    public SchedulerFactory schedulerFactory() {
        return new SchedulerFactory(environment);
    }
}

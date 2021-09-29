package com.bridle.core.properties.validation;

import com.bridle.core.components.http.HttpOutProperties;
import com.bridle.core.components.http.SimpleHttpOutEndpointConsumerBuilderFactory;
import com.bridle.core.components.sheduler.SchedulerProperties;
import com.bridle.core.components.sheduler.SimpleSchedulerEndpointConsumerBuilderFactory;
import com.bridle.core.configuration.SimpleBridleConfiguration;
import com.bridle.core.properties.PropertiesLoader;
import com.bridle.core.routebuilder.BaseRouteBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {SimpleBridleConfiguration.class})
@TestPropertySource(properties = {"spring.config.location=classpath:validation/scheduler-application.yml"})
public class ConfigurationPropertiesJsr303ValidatorTest {

    private static final int EXPECTED_INTEGER_VALUE = 1;
    private static final String EXPECTED_STRING_VALUE = "localhost:8080";
    @Autowired
    private PropertiesLoader propertiesLoader;

    @Test
    public void propertiesLoaderSuccessLoadsPropertiesWithJsr303Validation() {
        SchedulerProperties schedulerProperties = propertiesLoader.load(SchedulerProperties.class,
                SimpleSchedulerEndpointConsumerBuilderFactory.DEFAULT_CONFIG_PROPERTIES_KEY_SCHEDULER);
        assertEquals(EXPECTED_INTEGER_VALUE, schedulerProperties.getThreadCount());
        HttpOutProperties httpOutProperties = propertiesLoader.load(HttpOutProperties.class,
                SimpleHttpOutEndpointConsumerBuilderFactory.DEFAULT_CONFIG_PROPERTIES_KEY_HTTP_OUT);
        assertEquals(EXPECTED_STRING_VALUE, httpOutProperties.getUrl());
    }
}
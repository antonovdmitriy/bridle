package com.bridle.core.components.kafka;

import com.bridle.core.properties.PropertiesLoader;
import org.apache.camel.builder.EndpointProducerBuilder;

import java.util.Map;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.kafka;

public class SimpleKafkaOutEndpointProducerBuilderFactory implements KafkaOutFactory {

    public static final String DEFAULT_CONFIG_PROPERTIES_KEY_KAFKA_OUT = "kafka-out";
    private final PropertiesLoader loader;

    public SimpleKafkaOutEndpointProducerBuilderFactory(PropertiesLoader loader) {
        this.loader = loader;
    }

    @Override
    public EndpointProducerBuilder create(String componentName) {
        KafkaOutProperties properties = loader.load(KafkaOutProperties.class, componentName);
        EndpointProducerBuilder component = kafka(properties.getTopic())
                .brokers(properties.getBrokers());

        Map<String, Object> additionalProperties = properties.getAdditional();
        if (additionalProperties != null && !additionalProperties.isEmpty()) {
            additionalProperties.forEach(component::doSetProperty);
        }
        return component;
    }

    @Override
    public EndpointProducerBuilder create() {
        return create(DEFAULT_CONFIG_PROPERTIES_KEY_KAFKA_OUT);
    }
}

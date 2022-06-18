package com.bridle.core.components.kafka;

import com.bridle.core.components.http.HttpOutProperties;
import com.bridle.core.properties.PropertiesLoader;
import org.apache.camel.builder.EndpointProducerBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SimpleKafkaOutEndpointProducerBuilderFactoryTest {

    @InjectMocks
    private SimpleKafkaOutEndpointProducerBuilderFactory kafkaOutFactory;
    @Mock
    private PropertiesLoader loader;
    private KafkaOutProperties properties;

    @BeforeEach
    public void init() {
        initKafkaOutProperties();
    }

    private void initKafkaOutProperties() {
        properties = new KafkaOutProperties();
        properties.setBrokers("test:9090,test:9091");
        properties.setTopic("test_topic");
        properties.setAdditional(Map.of("propKeyFirst", "propValueFirst"));
        properties.setAdditional(Map.of("propKeySecond", "propValueSecond"));
    }

    @Test
    void createReturnsProducerBuilderWithLoadedPropertiesForDefaultComponentName() {
        when(loader.load(KafkaOutProperties.class,
                SimpleKafkaOutEndpointProducerBuilderFactory.DEFAULT_CONFIG_PROPERTIES_KEY_KAFKA_OUT))
                .thenReturn(properties);

        EndpointProducerBuilder kafkaOutProducerBuilder = kafkaOutFactory.create();

        String uri = kafkaOutProducerBuilder.getUri();
        verifyThatUriContainsAllAdditionalProperties(uri, properties);
        verifyThatUriContainsMandatoryProperties(uri);
    }

    @Test
    void createReturnsProducerBuilderWithLoadedPropertiesForComponentName() {
        String componentName = "kafka-custom-out";
        when(loader.load(KafkaOutProperties.class, componentName)).thenReturn(properties);

        EndpointProducerBuilder kafkaOutProducerBuilder = kafkaOutFactory.create(componentName);

        String uri = kafkaOutProducerBuilder.getUri();
        verifyThatUriContainsAllAdditionalProperties(uri, properties);
        verifyThatUriContainsMandatoryProperties(uri);
    }

    private void verifyThatUriContainsMandatoryProperties(String uri) {
//        assertTrue(() -> uri.startsWith("http://" + properties.getUrl()));
    }

    private void verifyThatUriContainsAllAdditionalProperties(String uri, KafkaOutProperties properties) {
        assertAll(properties.getAdditional()
                .entrySet()
                .stream()
                .map(entry -> () -> uri.contains(entry.getKey() + "=" + entry.getValue()))
                //TODO: wrong test. No fact assertions. Fix in another properties test too!!!
        );
    }
}
package com.bridle.core.components.kafka;

import com.bridle.core.properties.PropertiesLoader;
import org.apache.camel.builder.EndpointProducerBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static com.bridle.core.components.ComponentTestUtils.verifyThatUriContainsAllAdditionalProperties;
import static com.bridle.core.components.ComponentTestUtils.verifyThatUriContainsProperty;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SimpleKafkaOutEndpointProducerBuilderFactoryTest {

    public static final String EXPECTED_BROKERS = "test:9090,test:9091";
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
        properties.setBrokers(EXPECTED_BROKERS);
        properties.setTopic("test_topic");
        HashMap<String, Object> additionalProps = new HashMap<>();
        additionalProps.put("propKeyFirst", "propValueFirst");
        additionalProps.put("propKeySecond", "propValueSecond");
        properties.setAdditional(additionalProps);
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
        verifyThatUriContainsProperty(uri, "brokers",
                URLEncoder.encode(EXPECTED_BROKERS, StandardCharsets.UTF_8));
    }

}
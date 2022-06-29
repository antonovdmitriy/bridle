package com.bridle.core.components;

import com.bridle.core.properties.AbstractProperties;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComponentTestUtils {
    public static void verifyThatUriContainsProperty(String uri, String property, Object value) {
        assertTrue(() -> uri.contains(property + "=" + value));
    }

    public static void verifyThatUriContainsAllAdditionalProperties(String uri, AbstractProperties properties) {
        properties.getAdditional()
                .forEach((key, value) -> assertTrue(() -> uri.contains(key + "=" + value)));
    }
}

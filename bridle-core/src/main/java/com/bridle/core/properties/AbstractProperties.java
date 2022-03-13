package com.bridle.core.properties;

import java.util.Map;

public abstract class AbstractProperties {

    private Map<String, Object> additional;

    public Map<String, Object> getAdditional() {
        return additional;
    }

    public void setAdditional(Map<String, Object> additional) {
        this.additional = additional;
    }
}

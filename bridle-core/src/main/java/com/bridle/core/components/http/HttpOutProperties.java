package com.bridle.core.components.http;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

public class HttpOutProperties {

    @NotEmpty
    private String url;
    private Map<String, Object> additional;

    public Map<String, Object> getAdditional() {
        return additional;
    }

    public void setAdditional(Map<String, Object> additional) {
        this.additional = additional;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

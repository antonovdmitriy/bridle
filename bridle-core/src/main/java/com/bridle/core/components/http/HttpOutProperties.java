package com.bridle.core.components.http;

import javax.validation.constraints.NotEmpty;

public class HttpOutProperties {

    @NotEmpty
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

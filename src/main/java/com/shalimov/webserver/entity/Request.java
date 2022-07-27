package com.shalimov.webserver.entity;

import java.util.Map;

public class Request {
    private HttpMethod httpMethod;
    private String uri;
    private Map<String, String> headers;

    public void setHttpMethod(HttpMethod method) {
        this.httpMethod = method;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getUri() {
        return uri;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}

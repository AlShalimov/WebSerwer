package com.shalimov.webserver.entity;

public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    PATCH("PATCH");

    private final String description;

    HttpMethod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

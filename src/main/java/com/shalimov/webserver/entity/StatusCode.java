package com.shalimov.webserver.entity;

public enum StatusCode {
    OK(200, "200 OK"),

    BAD_REQUEST(401, "401 BAD REQUEST"),

    METHOD_NOT_ALLOWED(405, "405 METHOD NOT ALLOWED");

    private final int code;
    private final String statusText;

    StatusCode(int code, String statusText) {
        this.code = code;
        this.statusText = statusText;
    }

    public String getStatusText() {
        return "HTTP/1.1" + " " + code + " " + statusText;
    }

    public int getCode() {
        return code;
    }
}

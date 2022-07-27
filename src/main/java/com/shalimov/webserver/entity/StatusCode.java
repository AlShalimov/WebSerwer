package com.shalimov.webserver.entity;

public enum StatusCode {
    OK(200, "200 OK"),

    NOT_FOUND(404, "404 NOT FOUND"),

    INTERNAL_SERVER_ERROR(500, "500 INTERNAL SERVER ERROR"),

    BAD_REQUEST(401, "401 BAD REQUEST"),

    METHOD_NOT_ALLOWED(405, "405 METHOD NOT ALLOWED");

    private int code;
    private final String statusText;


    StatusCode(int i, String statusText) {
        this.code = code;
        this.statusText = statusText;
    }

    public String getStatusText() {
        return statusText;
    }

    public int getCode() {
        return code;
    }
}

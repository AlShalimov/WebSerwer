package com.shalimov.webserver.entity;

public class Response {
    private StatusCode statusCode;

    private byte[] content;

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public byte[] getContent() {
        return content;
    }
}

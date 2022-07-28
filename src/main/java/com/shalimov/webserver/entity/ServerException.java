package com.shalimov.webserver.entity;

public class ServerException extends RuntimeException {
    private StatusCode statusCode;

    public ServerException(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public ServerException(Throwable cause, StatusCode statusCode) {
        super(cause);
        this.statusCode = statusCode;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }
}

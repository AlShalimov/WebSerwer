package com.shalimov.webserver.response;

import com.shalimov.webserver.entity.Response;
import com.shalimov.webserver.entity.StatusCode;

import java.io.BufferedOutputStream;
import java.io.IOException;

import static com.shalimov.webserver.response.ResponseWriter.writeErrorResponse;
import static com.shalimov.webserver.response.ResponseWriter.writeResponse;

public class ResponseBuilder {
    public void responseWriter(BufferedOutputStream outputStream, Response response) throws IOException {
        try (outputStream) {
            StatusCode status = response.getStatusCode();
            if (status == StatusCode.OK) {
                writeResponse(outputStream, response);
            } else {
                writeErrorResponse(outputStream, response);
            }
        }
    }
}


package com.shalimov.webserver.response;

import com.shalimov.webserver.entity.Response;

import java.io.BufferedOutputStream;
import java.io.IOException;

public class ResponseWriter {
    private static final String LINE_END = "\r\n";

    public static void writeResponse(BufferedOutputStream socketOutputStream, Response response) throws IOException {
        socketOutputStream.write(("HTTP/1.1 " + response.getStatusCode().getStatusText()).getBytes());
        socketOutputStream.write(LINE_END.getBytes());
        socketOutputStream.write(LINE_END.getBytes());
        socketOutputStream.write(response.getContent());
        socketOutputStream.flush();
    }

    public static void writeErrorResponse(BufferedOutputStream socketOutputStream, Response response) throws IOException {
        socketOutputStream.write(("HTTP/1.1 " + response.getStatusCode().getStatusText()).getBytes());
        socketOutputStream.write(LINE_END.getBytes());
        socketOutputStream.write(LINE_END.getBytes());
        socketOutputStream.flush();
    }
}

package com.shalimov.webserver.request;
import com.shalimov.webserver.entity.HttpMethod;
import com.shalimov.webserver.entity.Request;
import com.shalimov.webserver.entity.ServerException;

import java.io.BufferedReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import static com.shalimov.webserver.InspectFromException.inspectRequestStartLine;
import static com.shalimov.webserver.entity.StatusCode.BAD_REQUEST;
import static com.shalimov.webserver.entity.StatusCode.METHOD_NOT_ALLOWED;

public class RequestParser {
    private final BufferedReader reader;

    public RequestParser(BufferedReader reader) {
        this.reader = reader;
    }

    public Request parseRequest() {
        Request request = new Request();

        try {
            injectUriAndMethod(reader, request);
            injectHeaders(reader, request);
            return request;

        } catch (IOException e) {
            throw new ServerException( BAD_REQUEST);
        }
    }

    static void injectUriAndMethod(BufferedReader reader, Request request) throws IOException {
        String requestStartLine = reader.readLine();
        inspectRequestStartLine(requestStartLine);
        String[] requestStartLineElements = requestStartLine.split(" ");

        String requestHttpMethod = requestStartLineElements[0];
        for (HttpMethod httpMethod : HttpMethod.values()) {
            if (requestHttpMethod.equals(httpMethod.getDescription())) {
                if (!requestHttpMethod.equals("GET")) {
                    throw new ServerException(METHOD_NOT_ALLOWED);
                }
                request.setHttpMethod(httpMethod);
                break;
            }
        }
        request.setUri(requestStartLineElements[1]);
    }

    static void injectHeaders(BufferedReader reader, Request request) throws IOException {
        Map<String, String> headers = new HashMap<>();

        String headerLine;

        while ((headerLine = reader.readLine()) != null) {
            if (headerLine.isEmpty()) {
                break;
            }
            String[] headerLineElements = headerLine.split(": ");
            headers.put(headerLineElements[0], headerLineElements[1]);
        }
        request.setHeaders(headers);
    }

}

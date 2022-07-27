package com.shalimov.webserver.request;

import com.shalimov.webserver.entity.Request;
import com.shalimov.webserver.entity.Response;
import com.shalimov.webserver.entity.StatusCode;
import com.shalimov.webserver.resource.ResourceReader;
import com.shalimov.webserver.response.ResponseBuilder;
import com.shalimov.webserver.entity.ServerException;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;


public class RequestHandler {

    private final BufferedReader reader;
    private final BufferedOutputStream outputStream;
    private final ResourceReader resourceReader;

    public RequestHandler(BufferedReader reader, BufferedOutputStream outputStream, ResourceReader resourceReader) {
        this.reader = reader;
        this.outputStream = outputStream;
        this.resourceReader = resourceReader;
    }

    public void handle() throws IOException {
        RequestParser requestParser = new RequestParser(reader);
        ResponseBuilder responseBuilder = new ResponseBuilder();
        Response response = new Response();
        try {
            Request request = requestParser.parseRequest();
            byte[] byteContentFromResourceFile = resourceReader.readResource(request.getUri());
            response.setStatusCode(StatusCode.OK);
            response.setContent(byteContentFromResourceFile);
            responseBuilder.responseWriter(outputStream, response);
        } catch (ServerException e) {
            response.setStatusCode(e.getStatusCode());
        }
    }
}

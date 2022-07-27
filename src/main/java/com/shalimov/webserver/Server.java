package com.shalimov.webserver;

import com.shalimov.webserver.request.RequestHandler;
import com.shalimov.webserver.resource.ResourceReader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int DEFAULT_PORT = 3000;
    private static final String WEBAPP_PATH = "src/main/resources";
    private int port;

    public Server() {
        this(DEFAULT_PORT);
    }

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ResourceReader resourceReader = new ResourceReader(WEBAPP_PATH);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream())
                ) {
                    RequestHandler requestHandler = new RequestHandler(reader, outputStream, resourceReader);
                    requestHandler.handle();
                }
            }
        }
    }

    public void setPort(int port) {
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("Port " + port + " is out of range [0, 65535]");
        }
        this.port = port;
    }

    public int getPort() {
        return port;
    }
}



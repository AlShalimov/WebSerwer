package com.shalimov.webserver.request;


import com.shalimov.webserver.entity.Request;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;

import static com.shalimov.webserver.request.RequestParser.injectUriAndMethod;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class RequestParserTest {
    private Request request;

    @Before
    public void setUp() {
        request = new Request();
    }

    @Test
  public   void testInjectMethodIntoRequestObject() throws IOException {
        injectUriAndMethod(new BufferedReader(
                new CharArrayReader(("GET /index.html HTTP/1.1").toCharArray())), request);
        String actualHttpMethod = request.getHttpMethod().getDescription();
        assertEquals("GET", actualHttpMethod);
    }

    @Test
   public void testInjectUriIntoRequestObject() throws IOException {
        injectUriAndMethod(new BufferedReader(
                new CharArrayReader(("GET /index.html HTTP/1.1").toCharArray())), request);
        String actualUri = request.getUri();
        assertEquals("/index.html", actualUri);
    }

    @Test
   public void testInjectUriAndMethodThrowsRuntimeExceptionOnBlankStartLine(){
        assertThrows(RuntimeException.class,
                () -> injectUriAndMethod(new BufferedReader(
                        new CharArrayReader((" ").toCharArray())), request));
    }
}
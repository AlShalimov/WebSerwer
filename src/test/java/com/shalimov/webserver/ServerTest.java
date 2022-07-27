package com.shalimov.webserver;

import org.junit.Test;
import static org.junit.Assert.assertThrows;


public class ServerTest {
    Server server = new Server();
    @Test
    public void testSetPortWhenPortIsMoreThan_65535_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                server.setPort(80000));
    }

    @Test
    public void testSetPortWhenPortIsLessThanZeroThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                server.setPort(-3000));
    }

}
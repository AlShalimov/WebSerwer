package com.shalimov.webserver.resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


class ResourceReaderTest {
    private static final String ACTUAL_RESOURCE_DATA = "Yesterday all my troubles seemed so far away. " +
            "Now it looks as though they're here to stay. " +
            "Oh, I believe in yesterday. ";
    private final ResourceReader resourceReader = new ResourceReader("src/main/resources/webapp");

    @Before
    void init() throws Exception {
        File path = new File("src/main/resources/webapp/Test");
        path.mkdir();
        File path1 = new File("src/main/resources/webapp/Test/file1.txt");
        path1.createNewFile();
        OutputStream outputStream = new FileOutputStream(path1);
        byte[] contentArray = ACTUAL_RESOURCE_DATA.getBytes();
        outputStream.write(contentArray);
        outputStream.close();
    }

    @After
    void delete() {
        File path1 = new File("src/main/resources/webapp/Test/file1.txt");
        path1.delete();
        File path = new File("src/main/resources/webapp/Test");
        path.delete();
    }

    @Test
    void test_ReadResource_Return_Correct_Data_Read_From_Resource_File() throws IOException {
        String uri = "/Test/file1.txt";
        String resourceData = new String(resourceReader.readResource(uri));
        assertEquals(ACTUAL_RESOURCE_DATA, resourceData);
    }

    @Test
    void testReadResourceThrowsFileNotFoundExceptionOnIncorrectFilepath() {
        String uri = "/Test/file232.txt";
        assertThrows(FileNotFoundException.class,
                () -> resourceReader.readResource(uri));
    }

    @Test
    void testReadResourceThrowsRuntimeExceptionOnBlankResourceFileData() throws IOException {
        File path1 = new File("src/main/resources/webapp/file1.txt");
        path1.createNewFile();
        OutputStream outputStream = new FileOutputStream(path1);
        byte[] contentArray = (" ").getBytes();
        outputStream.write(contentArray);
        outputStream.close();
        String uri = "/file1.txt";
        assertThrows(RuntimeException.class,
                () -> resourceReader.readResource(uri));
        path1.delete();
    }
}
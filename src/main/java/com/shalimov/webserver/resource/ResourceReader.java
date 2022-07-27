package com.shalimov.webserver.resource;


import java.io.*;



import static com.shalimov.webserver.InspectFromException.inspectContentFromResource;
import static com.shalimov.webserver.InspectFromException.inspectPathIsExisted;

public class ResourceReader {

    private final String webappPath;

    public ResourceReader(String webappPath) {
        this.webappPath = webappPath;
    }

    public byte[] readResource(String uri) throws IOException {
        File resource = new File(webappPath, uri);
        inspectPathIsExisted(resource);

        try (InputStream byteContentInputStream = new BufferedInputStream(new FileInputStream(resource))) {
            return readContent(byteContentInputStream);
        }
    }

    private byte[] readContent(InputStream byteContentInputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int count;
        byte[] data = new byte[8 * 1024];
        while ((count = byteContentInputStream.read(data)) != -1) {
            buffer.write(data, 0, count);
        }
        inspectContentFromResource(buffer.toString());
        return buffer.toByteArray();
    }
}
package com.shalimov.webserver;

import java.io.File;
import java.io.FileNotFoundException;

public class InspectFromException {
    public static void inspectRequestStartLine(String requestStartLine) {
        if (requestStartLine == null || requestStartLine.isBlank()) {
            throw new RuntimeException("start line is NULL or blank");
        }
    }

    public static boolean inspectPathIsExisted(File resource) throws FileNotFoundException {
        if (!resource.exists()){
            throw new FileNotFoundException("File is not found");
        }
        return resource.exists();
    }

    public static void inspectContentFromResource(String content) {
        if (content.isBlank()) {
            throw new RuntimeException("Content from the resource file is blank");
        }
    }
}

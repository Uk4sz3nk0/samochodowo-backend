package com.lukaszwodniak.samochodowo.integration;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;

public abstract class BaseIntegrationTests {

    private static final String CHARSET = "UTF-8";
    private static final String REQUEST_FILES_PATH = "classpath:/requests";
    private static final String RESPONSE_FILES_PATH = "classpath:/responses";

    protected String getRequest(String fileName) {
        try {
            return FileUtils.readFileToString(new File(REQUEST_FILES_PATH + fileName), CHARSET);
        } catch (Exception e) {
            System.out.println("Error during getting request. Reason: " + e.getLocalizedMessage());
            return null;
        }
    }

    protected String getResponse(String fileName) {
        try {
            return getResourceFile(RESPONSE_FILES_PATH, fileName);
        } catch (Exception e) {
            System.out.println("Error during getting file. Reason: " + e.getLocalizedMessage());
            return null;
        }
    }

    private String getResourceFile(String path, String filename) throws Exception {
        return FileUtils.readFileToString(ResourceUtils.getFile(path + File.separator + filename), CHARSET);
    }
}

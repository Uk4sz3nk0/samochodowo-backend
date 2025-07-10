package com.lukaszwodniak.samochodowo.integration;

import com.lukaszwodniak.samochodowo.TestCommons;
import com.lukaszwodniak.samochodowo.annotations.SamochodowoIntegrationTest;
import com.lukaszwodniak.samochodowo.repository.ModelsRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.util.ResourceUtils;

import java.io.File;

@SamochodowoIntegrationTest
public abstract class BaseIntegrationTests extends TestCommons {

    private static final String CHARSET = "UTF-8";
    private static final String REQUEST_FILES_PATH = "classpath:" + File.separator + "requests";
    private static final String RESPONSE_FILES_PATH = "classpath:" + File.separator + "responses";
    protected static final String MANUFACTURER_ID = "949bb4ec-9c16-4524-82ab-7ddacb3580a7";

    @MockitoBean
    protected ModelsRepository modelsRepository;

    protected String getRequest(String fileName) {
        try {
            return FileUtils.readFileToString(new File(REQUEST_FILES_PATH + fileName), CHARSET);
        } catch (Exception e) {
            System.out.println("Error during getting request. Reason: " + e.getLocalizedMessage());
            return null;
        }
    }

    protected String getRequest(String directory, String filename) {
        return getRequest(File.separator + directory + File.separator + filename);
    }

    protected String getResponse(String directory, String filename) {
        try {
            return getResourceFile(RESPONSE_FILES_PATH + File.separator + directory + File.separator + filename);
        } catch (Exception e) {
            System.out.println("Error during getting file. Reason: " + e.getLocalizedMessage());
            return null;
        }
    }

    private String getResourceFile(String path) throws Exception {
        return FileUtils.readFileToString(ResourceUtils.getFile(path), CHARSET);
    }
}

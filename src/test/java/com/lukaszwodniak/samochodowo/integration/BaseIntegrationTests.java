package com.lukaszwodniak.samochodowo.integration;

import com.lukaszwodniak.samochodowo.TestCommons;
import com.lukaszwodniak.samochodowo.annotations.SamochodowoIntegrationTest;
import com.lukaszwodniak.samochodowo.models.entity.Manufacturer;
import com.lukaszwodniak.samochodowo.repository.ManufacturersRepository;
import com.lukaszwodniak.samochodowo.repository.ModelsRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;

@SamochodowoIntegrationTest
public abstract class BaseIntegrationTests extends TestCommons {

    private static final String CHARSET = "UTF-8";
    private static final String REQUEST_FILES_PATH = "classpath:" + File.separator + "requests";
    private static final String RESPONSE_FILES_PATH = "classpath:" + File.separator + "responses";

    @MockitoBean
    protected ModelsRepository modelsRepository;

    @MockitoBean
    protected ManufacturersRepository manufacturersRepository;

    protected String getRequest(String directory, String filename) {
        try {
            return getResourceFile(REQUEST_FILES_PATH + File.separator + directory + File.separator + filename);
        } catch (Exception e) {
            System.out.println("Error during getting file. Reason: " + e.getLocalizedMessage());
            return null;
        }
    }

    protected String getResponse(String directory, String filename) {
        try {
            return getResourceFile(RESPONSE_FILES_PATH + File.separator + directory + File.separator + filename);
        } catch (Exception e) {
            System.out.println("Error during getting file. Reason: " + e.getLocalizedMessage());
            return null;
        }
    }

    protected Manufacturer givenManufacturer(UUID id, String name) {
        var manufacturer = generateManufacturer(id, name);
        given(manufacturersRepository.findById(id)).willReturn(Optional.of(manufacturer));

        return manufacturer;
    }

    private String getResourceFile(String path) throws Exception {
        return FileUtils.readFileToString(ResourceUtils.getFile(path), CHARSET);
    }
}

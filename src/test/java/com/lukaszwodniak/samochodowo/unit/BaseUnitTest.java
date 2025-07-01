package com.lukaszwodniak.samochodowo.unit;

import com.lukaszwodniak.samochodowo.models.entity.Manufacturer;
import com.lukaszwodniak.samochodowo.repository.ManufacturersRepository;
import com.lukaszwodniak.samochodowo.repository.ModelsRepository;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public abstract class BaseUnitTest {

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

    protected Manufacturer generateManufacturer(UUID id, String name) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(name);
        manufacturer.setId(id);
        return manufacturer;
    }

    private String getResourceFile(String path, String filename) throws Exception {
        return FileUtils.readFileToString(ResourceUtils.getFile(path + File.separator + filename), CHARSET);
    }
}

package com.lukaszwodniak.samochodowo.integration;

import com.lukaszwodniak.samochodowo.models.entity.Manufacturer;
import com.lukaszwodniak.samochodowo.models.entity.Model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ModelsIntegrationTests extends BaseIntegrationTests {

    private static final String DIRECTORY = "models";
    private static final String FORD_MANUFACTURER = "Ford";
    private final PageRequest pageRequest = PageRequest.of(0, 10);

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetModels() throws Exception {
        givenModels();
        mockMvc.perform(get("/models").content(MediaType.APPLICATION_JSON_VALUE)
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(content().json(getResponse(DIRECTORY, "get-models.json")))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    void shouldGetFocusAndRaptorModels() throws Exception {
    }

    @Test
    void shouldGetById() throws Exception {
    }

    @Test
    void shouldNotGetById() throws Exception {
    }

    @Test
    void shouldAddNewModel() throws Exception {
    }

    @Test
    void shouldNotAddNewModel() throws Exception {
    }

    @Test
    void shouldUpdateModel() throws Exception {
    }

    @Test
    void shouldNotUpdateModel() throws Exception {
    }

    @Test
    void shouldDeleteModel() throws Exception {
    }

    private List<Model> prepareModels() {
        var manufacturer = new Manufacturer(UUID.fromString(MANUFACTURER_ID), FORD_MANUFACTURER, List.of());
        return prepareModels(manufacturer);
    }

    private List<Model> prepareModels(Manufacturer manufacturer) {
        return List.of(
                new Model(UUID.fromString("cbb889de-e712-4bf6-9260-7fb7865cb0d2"), "Focus", manufacturer, "MK7"),
                new Model(UUID.fromString("d3fc1dd2-c9d0-43a1-9b8a-8d3dfcd25c1f"), "F-150", manufacturer, "MK2"),
                new Model(UUID.fromString("edb606ec-2b03-408b-abe5-94907b8929d9"), "Raptor", manufacturer, "MK4"),
                new Model(UUID.fromString("4b2fb0e9-5080-4c7c-8ce1-d3c84ec82dd1"), "Explorer", manufacturer, "MK3"),
                new Model(UUID.fromString("4df86ffa-848e-45e1-a50f-5f3e34de01b6"), "Mondeo", manufacturer, "MK5")
        );
    }

    private void givenModels() {
        var models = prepareModels();
        var pagedModels = new PageImpl<>(models, pageRequest, models.size());
        given(modelsRepository.findAll(any(Pageable.class))).willReturn(pagedModels);
    }
}

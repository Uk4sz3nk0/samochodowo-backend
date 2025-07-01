package com.lukaszwodniak.samochodowo.unit;

import com.lukaszwodniak.samochodowo.models.entity.Manufacturer;
import com.lukaszwodniak.samochodowo.models.entity.Model;
import com.lukaszwodniak.samochodowo.repository.ModelsRepository;
import com.lukaszwodniak.samochodowo.service.ModelsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ModelsUnitTests extends BaseUnitTest {

    @Mock
    ModelsRepository modelsRepository;

    @InjectMocks
    ModelsService modelsService;

    @Test
    void shouldFind5Models() {
        Manufacturer manufacturer = generateManufacturer(UUID.randomUUID(), "Toyota");
        List<Model> models = new ArrayList<>(getMockModels(manufacturer)).stream()
                .sorted(Comparator.comparing(Model::getName))
                .toList();
        PageRequest pageable = PageRequest.of(0, 5);
        Page<Model> pagedModels = new PageImpl<>(models, pageable, pageable.getPageSize());

        when(modelsRepository.findAllByManufacturer(eq(manufacturer), any(Pageable.class))).thenReturn(pagedModels);

        Page<Model> results = modelsService.getByManufacturer(manufacturer, pageable);

        assertEquals(5, results.getTotalElements());
        assertEquals("Auris", results.getContent()
                .getFirst()
                .getName());

    }

    @Test
    void shouldFindAygoModels() {
        Manufacturer toyota = generateManufacturer(UUID.randomUUID(), "Toyota");
        List<Model> models = new ArrayList<>(getMockModels(toyota)).stream()
                .filter(model -> model.getName()
                        .toLowerCase()
                        .contains("aygo"))
                .toList();
        PageRequest pageable = PageRequest.of(0, 2);
        Page<Model> pagedModels = new PageImpl<>(models, pageable, pageable.getPageSize());

        when(modelsRepository.findAllByNameContainingIgnoreCase(eq("aygo"), any(Pageable.class))).thenReturn(pagedModels);

        Page<Model> results = modelsService.getModels(pageable, "aygo");
        assertEquals(2, results.getTotalElements());
        assertEquals("Aygo", results.getContent()
                .getFirst()
                .getName());
        assertEquals("Aygo X", results.getContent()
                .get(1)
                .getName());
    }

    private List<Model> getMockModels(Manufacturer manufacturer) {
        return List.of(
                new Model(UUID.randomUUID(), "Yaris", manufacturer, ""),
                new Model(UUID.randomUUID(), "Auris", manufacturer, ""),
                new Model(UUID.randomUUID(), "Aygo", manufacturer, ""),
                new Model(UUID.randomUUID(), "Avensis", manufacturer, ""),
                new Model(UUID.randomUUID(), "Aygo X", manufacturer, ""),
                new Model(UUID.randomUUID(), "C-HR", manufacturer, ""),
                new Model(UUID.randomUUID(), "Cramry", manufacturer, ""),
                new Model(UUID.randomUUID(), "Mirai", manufacturer, ""),
                new Model(UUID.randomUUID(), "Celica", manufacturer, ""),
                new Model(UUID.randomUUID(), "Corolla", manufacturer, ""),
                new Model(UUID.randomUUID(), "Hilux", manufacturer, ""),
                new Model(UUID.randomUUID(), "Highlander", manufacturer, "")
        );
    }
}

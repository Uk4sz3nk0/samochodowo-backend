package com.lukaszwodniak.samochodowo.unit;

import com.lukaszwodniak.samochodowo.errors.models.ModelAlreadyExistsException;
import com.lukaszwodniak.samochodowo.errors.models.NoSuchModelException;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ModelsServiceUnitTests extends BaseUnitTest {

    private static final String AYGO_MODEL = "Aygo";

    @Mock
    ModelsRepository modelsRepository;

    @InjectMocks
    ModelsService modelsService;

    @Test
    void shouldFind5Models() {
        Manufacturer manufacturer = generateManufacturer(UUID.randomUUID(), TOYOTA_MANUFACTURER);
        List<Model> models = new ArrayList<>(getMockModels()).stream()
                .sorted(Comparator.comparing(Model::getName))
                .limit(5)
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
        List<Model> models = new ArrayList<>(getMockModels()).stream()
                .filter(model -> model.getName()
                        .toLowerCase()
                        .contains(AYGO_MODEL.toLowerCase()))
                .toList();
        PageRequest pageable = PageRequest.of(0, 2);
        Page<Model> pagedModels = new PageImpl<>(models, pageable, pageable.getPageSize());

        when(modelsRepository.findAllByNameContainingIgnoreCase(eq(AYGO_MODEL), any(Pageable.class))).thenReturn(pagedModels);
        Page<Model> results = modelsService.getModels(pageable, AYGO_MODEL);

        assertEquals(2, results.getTotalElements());
        assertTrue(results.stream()
                .allMatch(model -> model.getName()
                        .contains(AYGO_MODEL)));
    }

    @Test
    void shouldFindModelById() {
        UUID uuid = UUID.randomUUID();
        var manufacturer = getToyotaManufacturer(UUID.randomUUID());
        var model = new Model(uuid, "GT86", manufacturer, "MK1");

        when(modelsRepository.findById(eq(uuid))).thenReturn(Optional.of(model));
        var foundModel = modelsService.getModel(uuid);

        assertEquals(uuid, foundModel.getId());
        assertEquals("GT86", foundModel.getName());
        assertEquals("MK1", foundModel.getGeneration());
    }

    @Test
    void shouldNotFindModelById() {
        UUID uuid = UUID.randomUUID();

        when(modelsRepository.findById(eq(uuid))).thenReturn(Optional.empty());

        assertThrows(NoSuchModelException.class, () -> modelsService.getModel(uuid));
    }

    @Test
    void shouldAddModel() {
        var uuid = UUID.randomUUID();
        var manufacturer = getToyotaManufacturer(UUID.randomUUID());
        var model = new Model(null, "GT86", manufacturer, "MK1");
        var savedModel = new Model(uuid, "GT86", manufacturer, "MK1");

        when(modelsRepository.existsByNameIgnoreCaseAndManufacturerAndGenerationIgnoreCase(eq("GT86"), eq(manufacturer), eq("MK1"))).thenReturn(false);
        when(modelsRepository.save(any(Model.class))).thenReturn(savedModel);

        var result = modelsService.addModel(model);
        assertEquals(uuid, result.getId());
        assertEquals("GT86", result.getName());
        assertEquals("MK1", result.getGeneration());
    }

    @Test
    void shouldNotAddModelByAlreadyExistingOne() {
        var manufacturer = getToyotaManufacturer(UUID.randomUUID());
        var model = new Model(null, "GT86", manufacturer, "MK1");

        when(modelsRepository.existsByNameIgnoreCaseAndManufacturerAndGenerationIgnoreCase(eq("GT86"), eq(manufacturer), eq("MK1"))).thenReturn(true);

        assertThrows(ModelAlreadyExistsException.class, () -> modelsService.addModel(model));
    }

    @Test
    void shouldUpdateModel() {
        var uuid = UUID.randomUUID();
        var manufacturer = getToyotaManufacturer(UUID.randomUUID());
        var model = new Model(uuid, "GT86", manufacturer, "MK1");

        when(modelsRepository.findById(eq(uuid))).thenReturn(Optional.of(model));
        model.setName("Corolla");
        when(modelsRepository.save(any(Model.class))).thenReturn(model);

        var result = modelsService.updateModel(model);
        assertEquals(uuid, result.getId());
        assertEquals("Corolla", result.getName());
    }

    @Test
    void shouldNotUpdateNotExistingModel() {
        var manufacturer = getToyotaManufacturer(UUID.randomUUID());
        var uuid = UUID.randomUUID();
        var model = new Model(uuid, "GT86", manufacturer, "MK1");

        when(modelsRepository.findById(eq(uuid))).thenReturn(Optional.empty());

        assertThrows(NoSuchModelException.class, () -> modelsService.updateModel(model));
    }

    @Test
    void shouldDeleteModel() {
        var uuid = UUID.randomUUID();

        assertDoesNotThrow(() -> modelsService.deleteModel(uuid));
    }

    private List<Model> getMockModels() {
        var manufacturer = getToyotaManufacturer(UUID.randomUUID());
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

package com.lukaszwodniak.samochodowo.controllers;

import com.lukaszwodniak.samochodowo.handlers.ModelsHandler;
import com.lukaszwodniak.samochodowo.models.dto.ModelDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * ModelsController
 * <br>
 * Created on: 2025-07-01
 *
 * @author Łukasz Wodniak
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/models")
public class ModelsController {

    private final ModelsHandler modelsHandler;

    @GetMapping
    public ResponseEntity<Page<ModelDto>> getModels(Pageable pageable, @RequestParam(required = false) String phrase) {
        log.debug("Request \"getModels\" has called");
        var models = modelsHandler.handleGetModels(pageable, phrase);
        return ResponseEntity.ok(models);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelDto> getModel(@PathVariable UUID id) {
        log.debug("Request \"getModel\" has called");
        var model = modelsHandler.handleGetModel(id);
        return ResponseEntity.ok(model);
    }

    @PostMapping
    public ResponseEntity<ModelDto> createModel(@RequestBody ModelDto modelDto) {
        log.debug("Request \"createModel\" has called");
        var addedModel = modelsHandler.handleCreateModel(modelDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(addedModel);
    }

    @PatchMapping
    public ResponseEntity<ModelDto> updateModel(@RequestBody ModelDto modelDto) {
        log.debug("Request \"updateModel\" has called");
        var updatedModel = modelsHandler.handleUpdateModel(modelDto);
        return ResponseEntity.ok(updatedModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable UUID id) {
        log.debug("Request \"deleteModel\" has called");
        modelsHandler.handleDeleteModel(id);
        return ResponseEntity.noContent()
                .build();
    }
}

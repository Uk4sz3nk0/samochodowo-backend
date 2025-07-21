package com.lukaszwodniak.samochodowo.handlers;

import com.lukaszwodniak.samochodowo.models.dto.ModelDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * ModelsHandler
 * <br>
 * Created on: 2025-07-02
 *
 * @author Łukasz Wodniak
 */

public interface ModelsHandler {

    Page<ModelDto> handleGetModels(Pageable pageable, String phrase);
    ModelDto handleGetModel(UUID id);
    ModelDto handleCreateModel(ModelDto model);
    ModelDto handleUpdateModel(ModelDto model);
    void handleDeleteModel(UUID id);
}

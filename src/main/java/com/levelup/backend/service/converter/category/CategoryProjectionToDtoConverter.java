package com.levelup.backend.service.converter.category;

import com.levelup.backend.api.model.dto.CategoryDto;
import com.levelup.backend.data.entity.Category;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CategoryProjectionToDtoConverter.class})
public interface CategoryProjectionToDtoConverter {

    @Nullable
    CategoryDto convert(@NotNull Category category);
}

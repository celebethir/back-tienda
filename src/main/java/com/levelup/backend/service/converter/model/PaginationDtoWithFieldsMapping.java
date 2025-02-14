package com.levelup.backend.service.converter.model;

import com.levelup.backend.api.model.dto.PaginationDto;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Collections;
import java.util.Map;

public record PaginationDtoWithFieldsMapping(@Nonnull PaginationDto paginationDto, @Nullable Map<String, String> fieldsMapping) {
    public PaginationDtoWithFieldsMapping(@Nonnull final PaginationDto paginationDto) {
        this(paginationDto, Collections.emptyMap());
    }

    public PaginationDtoWithFieldsMapping(@Nonnull PaginationDto paginationDto, @Nullable Map<String, String> fieldsMapping) {
        this.paginationDto = paginationDto;
        this.fieldsMapping = fieldsMapping;
    }

    @Nonnull
    public PaginationDto paginationDto() {
        return this.paginationDto;
    }

    @Nullable
    public Map<String, String> fieldsMapping() {
        return this.fieldsMapping;
    }
}

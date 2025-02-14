package com.levelup.backend.service.converter.model;

import com.levelup.backend.api.model.dto.SortDto;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Collections;
import java.util.Map;

public record SortDtoWithFieldsMapping(@Nonnull SortDto sortDto, @Nullable Map<String, String> fieldsMapping) {
    public SortDtoWithFieldsMapping(@Nonnull final SortDto sortDto) {
        this(sortDto, Collections.emptyMap());
    }

    public SortDtoWithFieldsMapping(@Nonnull SortDto sortDto, @Nullable Map<String, String> fieldsMapping) {
        this.sortDto = sortDto;
        this.fieldsMapping = fieldsMapping;
    }

    @Nonnull
    public SortDto sortDto() {
        return this.sortDto;
    }

    @Nullable
    public Map<String, String> fieldsMapping() {
        return this.fieldsMapping;
    }
}

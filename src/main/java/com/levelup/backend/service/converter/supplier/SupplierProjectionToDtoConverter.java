package com.levelup.backend.service.converter.supplier;

import com.levelup.backend.api.model.dto.SupplierDto;
import com.levelup.backend.data.entity.Supplier;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = SupplierProjectionToDtoConverter.class)
public interface SupplierProjectionToDtoConverter {

    @Nullable
    SupplierDto convert(@NotNull Supplier source);
}

package com.levelup.backend.service.converter.product;

import com.levelup.backend.api.model.dto.ProductDto;
import com.levelup.backend.data.entity.Product;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductProjectionToDtoConverter.class})
public interface ProductProjectionToDtoConverter {

    @Nullable
    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "supplierName", source = "supplier.name")
    ProductDto convert(@NotNull Product source);
}

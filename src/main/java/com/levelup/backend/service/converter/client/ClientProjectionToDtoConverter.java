package com.levelup.backend.service.converter.client;

import com.levelup.backend.api.model.dto.ClientDto;
import com.levelup.backend.data.entity.Client;
import com.levelup.backend.service.converter.user.UserProjectionToDtoConverter;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserProjectionToDtoConverter.class})
public interface ClientProjectionToDtoConverter {

    @Nullable
    @Mapping(target = "username", source = "user.username")
    ClientDto convert(@NotNull Client source);
}

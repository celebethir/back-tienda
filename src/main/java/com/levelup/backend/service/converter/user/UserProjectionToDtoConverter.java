package com.levelup.backend.service.converter.user;

import com.levelup.backend.api.model.dto.UserDto;
import com.levelup.backend.data.entity.User;
import jakarta.annotation.Nullable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserProjectionToDtoConverter {

    @Mapping(target = "roleName", source = "role.name")
    @Nullable
    UserDto convert(User source);
}

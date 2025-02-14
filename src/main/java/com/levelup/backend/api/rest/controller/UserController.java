package com.levelup.backend.api.rest.controller;

import com.levelup.backend.api.model.dto.UserCreateDto;
import com.levelup.backend.api.model.dto.UserDto;
import com.levelup.backend.api.model.dto.UserFilterDto;
import com.levelup.backend.api.model.dto.UserUpdateDto;
import com.levelup.backend.service.converter.user.UserProjectionToDtoConverter;
import com.levelup.backend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserProjectionToDtoConverter userProjectionToDtoConverter;

    @Autowired
    public UserController(final UserService userService, UserProjectionToDtoConverter userProjectionToDtoConverter) {
        this.userService = userService;
        this.userProjectionToDtoConverter = userProjectionToDtoConverter;
    }

    @GetMapping
    public Page<UserDto> getAll(@Validated final UserFilterDto userFilterDto) {
        return userService.getPage(userFilterDto).map(userProjectionToDtoConverter::convert);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable final Long id) {
        return userProjectionToDtoConverter.convert(userService.findById(id));
    }

    @PostMapping
    public UserDto create(@RequestBody @Validated final UserCreateDto userCreateDto) {
        return userProjectionToDtoConverter.convert(userService.createUser(userCreateDto));
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable final Long id, @RequestBody @Validated final UserUpdateDto userUpdateDto) {
        return userProjectionToDtoConverter.convert(userService.updateUser(id, userUpdateDto));
    }
}

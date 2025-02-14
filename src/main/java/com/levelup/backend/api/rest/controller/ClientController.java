package com.levelup.backend.api.rest.controller;

import com.levelup.backend.api.model.dto.ClientCreateDto;
import com.levelup.backend.api.model.dto.ClientDto;
import com.levelup.backend.api.model.dto.ClientFilterDto;
import com.levelup.backend.api.model.dto.ClientUpdateDto;
import com.levelup.backend.service.client.ClientService;
import com.levelup.backend.service.converter.client.ClientProjectionToDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    private final ClientProjectionToDtoConverter clientProjectionToDtoConverter;

    @Autowired
    public ClientController(final ClientService clientService, final ClientProjectionToDtoConverter clientProjectionToDtoConverter) {
        this.clientService = clientService;
        this.clientProjectionToDtoConverter = clientProjectionToDtoConverter;
    }

    @GetMapping
    public Page<ClientDto> getAll(@Validated final ClientFilterDto clientFilterDto) {
        return clientService.getPage(clientFilterDto).map(clientProjectionToDtoConverter::convert);
    }

    @GetMapping("/{id}")
    public ClientDto get(@PathVariable final Long id) {
        return clientProjectionToDtoConverter.convert(clientService.findById(id));
    }

    @PostMapping
    public ClientDto create(@Validated @RequestBody final ClientCreateDto clientCreateDto) {
        return clientProjectionToDtoConverter.convert(clientService.createClient(clientCreateDto));
    }

    @PutMapping("/{id}")
    public ClientDto update(@PathVariable final Long id,
                            @Validated @RequestBody final ClientUpdateDto clientUpdateDto) {
        return clientProjectionToDtoConverter.convert(clientService.updateClient(id, clientUpdateDto));
    }
}

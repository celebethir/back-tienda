package com.levelup.backend.service.client;

import com.levelup.backend.api.model.dto.ClientCreateDto;
import com.levelup.backend.api.model.dto.ClientFilterDto;
import com.levelup.backend.api.model.dto.ClientUpdateDto;
import com.levelup.backend.api.rest.exception.ResourceNotFoundException;
import com.levelup.backend.data.access.repository.ClientRepository;
import com.levelup.backend.data.access.specification.ClientSpecification;
import com.levelup.backend.data.entity.Client;
import com.levelup.backend.data.entity.User;
import com.levelup.backend.service.converter.PaginationDtoWithFieldsMappingToPageableConverter;
import com.levelup.backend.service.converter.model.PaginationDtoWithFieldsMapping;
import com.levelup.backend.service.user.UserService;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final PaginationDtoWithFieldsMappingToPageableConverter paginationDtoToPageableConverter;
    private final UserService userService;

    @Autowired
    public ClientService(final ClientRepository clientRepository,
                         final PaginationDtoWithFieldsMappingToPageableConverter paginationDtoToPageableConverter,
                         final UserService userService) {
        this.clientRepository = clientRepository;
        this.paginationDtoToPageableConverter = paginationDtoToPageableConverter;
        this.userService = userService;
    }

    public Page<Client> getPage(final ClientFilterDto filterDto) {
        final Specification<Client> specification = ClientSpecification.filterBy(filterDto);

        final Pageable pageable = paginationDtoToPageableConverter.convert(new PaginationDtoWithFieldsMapping(filterDto));
        Objects.requireNonNull(pageable);

        return clientRepository.findAll(specification, pageable);
    }

    public Client findById(final Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found"));
    }

    public Client createClient(final ClientCreateDto clientCreateDto) {
        Objects.requireNonNull(clientCreateDto);

        final Client client = new Client();
        setClientParams(clientCreateDto, client);
        return clientRepository.save(client);
    }

    private static void setClientParams(ClientCreateDto clientCreateDto, Client client) {
        client.setName(clientCreateDto.getName());
        client.setLastname(clientCreateDto.getLastname());
        client.setEmail(clientCreateDto.getPhone());
        client.setEmail(clientCreateDto.getEmail());
    }

    public Client updateClient(@Nonnull final Long id, @Nonnull final ClientUpdateDto clientUpdateDto) {
        Objects.requireNonNull(clientUpdateDto);
        Objects.requireNonNull(id);

        User user = userService.findById(id);

        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        client.setUser(user);
        client.setName(clientUpdateDto.getName());
        client.setLastname(clientUpdateDto.getLastname());
        client.setPhone(clientUpdateDto.getPhone());
        client.setEmail(clientUpdateDto.getEmail());
        client.setBannedList(clientUpdateDto.getBannedList());
        return clientRepository.save(client);
    }
}

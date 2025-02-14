package com.levelup.backend.data.access.specification;

import com.levelup.backend.api.model.dto.ClientFilterDto;
import com.levelup.backend.data.entity.Client;
import org.springframework.data.jpa.domain.Specification;

public class ClientSpecification {
    private static final Specification<Client> EMPTY_SPECIFICATION = (root, query, cb) -> cb.conjunction();

    private ClientSpecification() {
    }

    public static Specification<Client> filterBy(final ClientFilterDto clientFilterDto) {
        if (clientFilterDto == null) {
            return EMPTY_SPECIFICATION;
        }

        return Specification
                .where(filterByName(clientFilterDto.getName()))
                .and(filterByLastname(clientFilterDto.getLastname()))
                .and(filterByEmail(clientFilterDto.getEmail()))
                .and(filterByPhone(clientFilterDto.getPhone()))
                .and(filterByIncludeBanned(clientFilterDto.getIncludeBanned()));
    }

    public static Specification<Client> filterByName(final String name) {
        if (name == null) {
            return EMPTY_SPECIFICATION;
        }

        return (root, query, cb) ->
                cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Client> filterByLastname(final String lastname) {
        if (lastname == null) {
            return EMPTY_SPECIFICATION;
        }

        return (root, query, cb) ->
                cb.like(cb.lower(root.get("lastname")), "%" + lastname.toLowerCase() + "%");
    }

    public static Specification<Client> filterByEmail(final String email) {
        if (email == null) {
            return EMPTY_SPECIFICATION;
        }

        return (root, query, cb) ->
                cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%");
    }

    public static Specification<Client> filterByPhone(final String phone) {
        if (phone == null) {
            return EMPTY_SPECIFICATION;
        }

        return (root, query, cb) ->
                cb.like(root.get("phone"), "%" + phone + "%");
    }

    public static Specification<Client> filterByIncludeBanned(final Boolean includeBanned) {
        if (includeBanned == null || !includeBanned) {
            return EMPTY_SPECIFICATION;
        }

        return ((root, query, cb) -> cb.isTrue(root.get("includeBanned")));
    }
}

package com.levelup.backend.data.access.specification;

import com.levelup.backend.api.model.dto.UserFilterDto;
import com.levelup.backend.data.entity.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    private static final Specification<User> EMPTY_SPECIFICATION = (root, query, cb) -> cb.conjunction();

    public static Specification<User> filterBy(final UserFilterDto userFilterDto) {
        if (userFilterDto == null) {
            return EMPTY_SPECIFICATION;
        }

        return (root, query, cb) -> cb.like(cb.lower(root.get("username")), "%" + userFilterDto.getUsername().toLowerCase() + "%");
    }
}

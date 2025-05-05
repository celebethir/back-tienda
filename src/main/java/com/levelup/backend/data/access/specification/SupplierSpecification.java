package com.levelup.backend.data.access.specification;

import com.levelup.backend.api.model.dto.SupplierFilterDto;
import com.levelup.backend.data.entity.Supplier;
import org.springframework.data.jpa.domain.Specification;

public class SupplierSpecification {
    private static final Specification<Supplier> EMPTY_SPECIFICATION = (root, query, cb) -> cb.conjunction();

    private SupplierSpecification() {}

    public static Specification<Supplier> filterBy(final SupplierFilterDto supplierFilterDto) {
        if (supplierFilterDto == null) {
            return EMPTY_SPECIFICATION;
        }

        return Specification.where(filterByName(supplierFilterDto.getName()));
    }

    public static Specification<Supplier> filterByName(final String name) {
        if (name == null) {
            return EMPTY_SPECIFICATION;
        }

        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }
}

package com.levelup.backend.data.access.specification;

import com.levelup.backend.api.model.dto.ProductFilterDto;
import com.levelup.backend.data.entity.*;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ProductSpecification {
    private static final Specification<Product> EMPTY_SPECIFICATION = (root, query, cb) -> cb.conjunction();

    private ProductSpecification() {
    }

    public static Specification<Product> filterBy(final ProductFilterDto productFilterDto) {
        if (productFilterDto == null) {
            return EMPTY_SPECIFICATION;
        }

        return Specification
                .where(filterByName(productFilterDto.getName()))
                .and(filterByCategory(productFilterDto.getCategories()))
                .and(filterBySupplier(productFilterDto.getSuppliers()))
                .and(filterByBarCode(productFilterDto.getBarCode()));
    }

    public static Specification<Product> filterByName(final String productName) {
        if (productName == null) {
            return EMPTY_SPECIFICATION;
        }

        return ((root, query, cb) -> cb.like(root.get("name"), "%" + productName + "%"));
    }

    public static Specification<Product> filterByCategory(final List<Long> categories) {
        if (categories == null || categories.isEmpty()) {
            return EMPTY_SPECIFICATION;
        }

        return (root, query, cb) -> {
            final Join<Product, Category> categoryJoin = root.join(Product_.CATEGORY);

            Predicate[] categoryPredicates = categories.stream()
                    .map(category -> cb.equal(categoryJoin.get(Category_.ID), category))
                    .toArray(Predicate[]::new);

            return cb.and(categoryPredicates);
        };
    }

    public static Specification<Product> filterBySupplier(final List<Long> suppliers) {
        if (suppliers == null || suppliers.isEmpty()) {
            return EMPTY_SPECIFICATION;
        }

        return ((root, query, cb) -> {
            final Join<Product, Supplier> supplierJoin = root.join(Product_.SUPPLIER);

            Predicate[] supplierPredicates = suppliers.stream()
                    .map(supplier -> cb.equal(supplierJoin.get(Supplier_.ID), supplier))
                    .toArray(Predicate[]::new);

            return cb.and(supplierPredicates);
        });
    }

    public static Specification<Product> filterByBarCode(final int barCode) {
        if (barCode < 0) {
            return EMPTY_SPECIFICATION;
        }

        return (root, query, cb) ->
                cb.equal(root.get("barCode"), barCode);
    }
}

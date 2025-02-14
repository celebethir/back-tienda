package com.levelup.backend.service.converter;

import com.levelup.backend.api.model.dto.PaginationDto;
import com.levelup.backend.service.converter.model.PaginationDtoWithFieldsMapping;
import com.levelup.backend.service.converter.model.SortDtoWithFieldsMapping;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class PaginationDtoWithFieldsMappingToPageableConverter implements Converter<PaginationDtoWithFieldsMapping, Pageable> {
    private static final int DEFAULT_PAGE_SIZE = 5;
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private final SortDtoToSortConverter sortDtoToSortConverter;

    @Autowired
    public PaginationDtoWithFieldsMappingToPageableConverter(final SortDtoToSortConverter sortDtoToSortConverter) {
        this.sortDtoToSortConverter = sortDtoToSortConverter;
    }

    @Nullable
    public Pageable convert(@Nonnull final PaginationDtoWithFieldsMapping source) throws ValidationException {
        PaginationDto paginationDto = source.paginationDto();
        Objects.requireNonNull(paginationDto);
        if (paginationDto.getOffset() == null && paginationDto.getLimit() == null && paginationDto.getSort() == null) {
            return Pageable.unpaged();
        } else {
            Sort sort = (Sort)Objects.requireNonNull(this.sortDtoToSortConverter.convert(new SortDtoWithFieldsMapping(paginationDto, source.fieldsMapping())));
            if (paginationDto.getOffset() == null && paginationDto.getLimit() == null) {
                try {
                    return PageRequest.of(0, Integer.MAX_VALUE, sort);
                } catch (IllegalArgumentException var8) {
                    IllegalArgumentException e = var8;
                    throw new ValidationException(e.getMessage(), e);
                }
            } else {
                int pageSize = (Integer) Optional.ofNullable(paginationDto.getLimit()).orElse(5);
                int pageNumber = 0;
                Integer offset = paginationDto.getOffset();
                if (offset != null) {
                    if (offset % pageSize != 0) {
                        throw new ValidationException("Invalid pagination offset: Only multiples of the pagination limit are supported");
                    }

                    pageNumber = offset / pageSize;
                }

                try {
                    return PageRequest.of(pageNumber, pageSize, sort);
                } catch (IllegalArgumentException var9) {
                    IllegalArgumentException e = var9;
                    throw new ValidationException(e.getMessage(), e);
                }
            }
        }
    }
}

package com.levelup.backend.service.converter;

import com.levelup.backend.api.model.dto.SortDto;
import com.levelup.backend.service.converter.model.SortDtoWithFieldsMapping;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.validation.ValidationException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

@Component
public class SortDtoToSortConverter implements Converter<SortDtoWithFieldsMapping, Sort> {
    private static final Pattern SORT_SEPARATOR = Pattern.compile(",");
    private static final String SORT_PREFIX_ASC = "+";
    private static final String SORT_PREFIX_DESC = "-";

    public SortDtoToSortConverter() {
    }

    @Nullable
    public Sort convert(@Nonnull final SortDtoWithFieldsMapping source) throws ValidationException {
        Objects.requireNonNull(source);
        SortDto sortDto = source.sortDto();
        if (!StringUtils.hasText(sortDto.getSort())) {
            return Sort.unsorted();
        } else {
            List<Sort.Order> orders = SORT_SEPARATOR.splitAsStream(sortDto.getSort()).map((part) -> {
                String fieldKey = part;
                Sort.Direction direction = Sort.Direction.ASC;
                if (part.startsWith("+")) {
                    fieldKey = part.substring("+".length());
                } else if (part.startsWith("-")) {
                    fieldKey = part.substring("-".length());
                    direction = Sort.Direction.DESC;
                }

                Map<String, String> fieldsMapping = source.fieldsMapping();
                String field = fieldsMapping != null && fieldsMapping.containsKey(fieldKey) ? (String)fieldsMapping.get(fieldKey) : fieldKey;
                if (!StringUtils.hasText(field)) {
                    throw new ValidationException("Unknown or empty sort field: " + part);
                } else {
                    return new Sort.Order(direction, field);
                }
            }).toList();
            return Sort.by(orders);
        }
    }
}

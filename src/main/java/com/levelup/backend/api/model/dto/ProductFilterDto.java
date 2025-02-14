package com.levelup.backend.api.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ProductFilterDto extends PaginationDto {
    private String name;
    private List<Long> categories;
    private List<Long> suppliers;
    private int barCode;
}

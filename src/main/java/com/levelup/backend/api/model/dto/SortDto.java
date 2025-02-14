package com.levelup.backend.api.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class SortDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private @Pattern(
            regexp = "^[+-]?[a-zA-Z]+(,[+-]?[a-zA-Z]+)*$",
            message = "Sort should be a comma-separated list of fields with optional prefix + or -"
    ) String sort;

    public SortDto() {
    }

    @Nullable
    public String getSort() {
        return this.sort;
    }

    public void setSort(final String sort) {
        this.sort = sort;
    }
}

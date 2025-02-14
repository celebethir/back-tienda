package com.levelup.backend.api.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class PaginationDto extends SortDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private @PositiveOrZero(
            message = "Pagination offset should be greater than or equal to zero"
    ) Integer offset;
    private @Min(
            value = 1L,
            message = "Pagination limit should be greater than or equal to one"
    ) Integer limit;

    public PaginationDto() {
    }

    public Integer getOffset() {
        return this.offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}

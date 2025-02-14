package com.levelup.backend.api.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private String categoryName;
    private String supplierName;
    private BigDecimal buyPrice;
    private BigDecimal sellPrice;
    private int barCode;
    private int inventory;
    private String image;
}

package com.levelup.backend.api.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class SupplierDto {
    private Long id;
    private String name;
    private String contact;
    private String email;
    private String web;
    private String phone;
    private int minimumOrder;
}

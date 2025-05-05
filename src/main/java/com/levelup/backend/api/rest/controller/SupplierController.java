package com.levelup.backend.api.rest.controller;

import com.levelup.backend.api.model.dto.SupplierDto;
import com.levelup.backend.api.model.dto.SupplierFilterDto;
import com.levelup.backend.service.converter.supplier.SupplierProjectionToDtoConverter;
import com.levelup.backend.service.supplier.SupplierService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService supplierService;
    private final SupplierProjectionToDtoConverter supplierProjectionToDtoConverter;

    @Autowired
    public SupplierController(SupplierService supplierService, SupplierProjectionToDtoConverter supplierProjectionToDtoConverter) {
        this.supplierService = supplierService;
        this.supplierProjectionToDtoConverter = supplierProjectionToDtoConverter;
    }

    @GetMapping
    public Page<SupplierDto> getAll(@Valid SupplierFilterDto supplierFilterDto) {
        return supplierService.getPage(supplierFilterDto).map(supplierProjectionToDtoConverter::convert);
    }
}

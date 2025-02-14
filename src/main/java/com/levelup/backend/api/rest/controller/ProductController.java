package com.levelup.backend.api.rest.controller;

import com.levelup.backend.api.model.dto.ProductCreateDto;
import com.levelup.backend.api.model.dto.ProductDto;
import com.levelup.backend.api.model.dto.ProductFilterDto;
import com.levelup.backend.api.model.dto.ProductUpdateDto;
import com.levelup.backend.service.converter.product.ProductProjectionToDtoConverter;
import com.levelup.backend.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductProjectionToDtoConverter productProjectionToDtoConverter;

    @Autowired
    public ProductController(ProductService productService, ProductProjectionToDtoConverter productProjectionToDtoConverter) {
        this.productService = productService;
        this.productProjectionToDtoConverter = productProjectionToDtoConverter;
    }

    @GetMapping
    public Page<ProductDto> getAll(@Validated final ProductFilterDto productFilterDto) {
        return productService.getPage(productFilterDto).map(productProjectionToDtoConverter::convert);
    }

    @GetMapping("/{id}")
    public ProductDto get(@PathVariable final Long id) {
        return productProjectionToDtoConverter.convert(productService.findById(id));
    }

    @PostMapping
    public ProductDto create(@Validated @RequestBody final ProductCreateDto productCreateDto) {
        return productProjectionToDtoConverter.convert(productService.createProduct(productCreateDto));
    }

    @PutMapping("/{id}")
    public ProductDto update(@PathVariable final Long id,
                             @Validated @RequestBody final ProductUpdateDto productUpdateDto) {
        return productProjectionToDtoConverter.convert(productService.updateProduct(id, productUpdateDto));
    }
}

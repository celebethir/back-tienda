package com.levelup.backend.api.rest.controller;

import com.levelup.backend.api.model.dto.CategoryDto;
import com.levelup.backend.service.category.CategoryService;
import com.levelup.backend.service.converter.category.CategoryProjectionToDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryProjectionToDtoConverter categoryProjectionToDtoConverter;

    @Autowired
    public CategoryController(final CategoryService categoryService,
                              final CategoryProjectionToDtoConverter categoryProjectionToDtoConverter) {
        this.categoryService = categoryService;
        this.categoryProjectionToDtoConverter = categoryProjectionToDtoConverter;
    }

    @GetMapping
    public List<CategoryDto> getCategories() {
        return categoryService.getAll().stream().map(CategoryProjectionToDtoConverter::convert);
    }
}

package com.levelup.backend.api.rest.controller;

import com.levelup.backend.api.model.dto.CategoryDto;
import com.levelup.backend.service.category.CategoryService;
import com.levelup.backend.service.converter.category.CategoryProjectionToDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return categoryService.getAll().stream().map(categoryProjectionToDtoConverter::convert).toList();
    }

    @GetMapping("/{id}")
    public CategoryDto get(@PathVariable final Long id) {
        return categoryProjectionToDtoConverter.convert(categoryService.findById(id));
    }

    @PostMapping
    public CategoryDto create(@RequestBody final CategoryDto categoryDto) {
        return categoryProjectionToDtoConverter.convert(categoryService.createCategory(categoryDto));
    }

    @PutMapping("/{id}")
    public CategoryDto update(@PathVariable final Long id, final @RequestBody CategoryDto categoryDto) {
        return categoryProjectionToDtoConverter.convert(categoryService.updateCategory(id, categoryDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        categoryService.deleteCategory(id);
    }
}

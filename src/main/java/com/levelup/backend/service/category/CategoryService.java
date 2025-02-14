package com.levelup.backend.service.category;

import com.levelup.backend.api.model.dto.CategoryDto;
import com.levelup.backend.api.rest.exception.ResourceNotFoundException;
import com.levelup.backend.data.access.repository.CategoryRepository;
import com.levelup.backend.data.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    public Category createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, CategoryDto categoryDto) {
        Category category = findById(id);
        category.setName(categoryDto.getName());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        findById(id);
        categoryRepository.deleteById(id);
    }
}

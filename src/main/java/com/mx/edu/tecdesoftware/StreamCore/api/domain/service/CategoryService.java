package com.mx.edu.tecdesoftware.StreamCore.api.domain.service;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Category;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int categoryId) {
        return categoryRepository.getCategory(categoryId);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}
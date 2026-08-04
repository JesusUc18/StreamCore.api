package com.mx.edu.tecdesoftware.StreamCore.api.domain.service;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Category;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.repository.CategoryRepository;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.repository.ContentRepository;
import com.mx.edu.tecdesoftware.StreamCore.api.web.exception.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ContentRepository contentRepository;

    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int categoryId) {
        return categoryRepository.getCategory(categoryId);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public boolean delete(int categoryId) {
        return getCategory(categoryId).map(category -> {
            if (contentRepository.existsByCategory(categoryId)) {
                throw new ConflictException("No se puede eliminar: la categoría '" + categoryId + "' tiene contenidos asociados.");
            }
            categoryRepository.delete(categoryId);
            return true;
        }).orElse(false);
    }
}
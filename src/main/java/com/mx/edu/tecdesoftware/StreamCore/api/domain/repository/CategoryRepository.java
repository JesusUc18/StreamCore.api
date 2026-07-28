package com.mx.edu.tecdesoftware.StreamCore.api.domain.repository;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    List<Category> getAll();
    Optional<Category> getCategory(int categoryId);
    Category save(Category category);
}
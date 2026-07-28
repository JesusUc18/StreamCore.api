package com.mx.edu.tecdesoftware.StreamCore.api.persistence;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Category;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.repository.CategoryRepository;
import com.mx.edu.tecdesoftware.StreamCore.api.persistence.crud.CategoriaCrudRepository;
import com.mx.edu.tecdesoftware.StreamCore.api.persistence.entity.Categoria;
import com.mx.edu.tecdesoftware.StreamCore.api.persistence.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaRepository implements CategoryRepository {

    @Autowired
    private CategoriaCrudRepository categoriaCrudRepository;

    @Autowired
    private CategoryMapper mapper;

    @Override
    public List<Category> getAll() {
        return mapper.toCategories((List<Categoria>) categoriaCrudRepository.findAll());
    }

    @Override
    public Optional<Category> getCategory(int categoryId) {
        return categoriaCrudRepository.findById(categoryId)
                .map(mapper::toCategory);
    }

    @Override
    public Category save(Category category) {
        Categoria categoria = mapper.toCategoria(category);
        categoria.setIdCategoria(null);
        return mapper.toCategory(categoriaCrudRepository.save(categoria));
    }
}
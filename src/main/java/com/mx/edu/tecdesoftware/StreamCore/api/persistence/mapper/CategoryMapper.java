package com.mx.edu.tecdesoftware.StreamCore.api.persistence.mapper;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Category;
import com.mx.edu.tecdesoftware.StreamCore.api.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "description"),
            @Mapping(source = "estado", target = "state")
    })
    Category toCategory(Categoria categoria);

    List<Category> toCategories(List<Categoria> categorias);

    @InheritInverseConfiguration
    @Mapping(target = "contenidos", ignore = true)
    Categoria toCategoria(Category category);
}
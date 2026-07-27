package com.mx.edu.tecdesoftware.StreamCore.api.persistence.mapper;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Content;
import com.mx.edu.tecdesoftware.StreamCore.api.persistence.entity.Contenido;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ContentMapper {

    @Mappings({
            @Mapping(source = "idContenido", target = "contentId"),
            @Mapping(source = "titulo", target = "title"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "categoria", target = "category"),
            @Mapping(source = "tipo", target = "type"),
            @Mapping(source = "anioLanzamiento", target = "releaseYear"),
            @Mapping(source = "duracionMinutos", target = "durationMinutes"),
            @Mapping(source = "temporadas", target = "seasons"),
            @Mapping(source = "clasificacion", target = "rating"),
            @Mapping(source = "estado", target = "state")
    })
    Content toContent(Contenido contenido);

    List<Content> toContents(List<Contenido> contenidos);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "categoria", ignore = true),
            @Mapping(target = "visualizaciones", ignore = true)
    })
    Contenido toContenido(Content content);
}
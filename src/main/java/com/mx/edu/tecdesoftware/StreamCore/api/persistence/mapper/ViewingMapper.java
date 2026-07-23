package com.mx.edu.tecdesoftware.StreamCore.api.persistence.mapper;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Viewing;
import com.mx.edu.tecdesoftware.StreamCore.api.persistence.entity.SuscripcionContenido;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ViewingMapper {

    @Mappings({
            @Mapping(source = "idContenido", target = "contentId"),
            @Mapping(source = "fechaVisualizacion", target = "viewedAt"),
            @Mapping(source = "minutosVistos", target = "minutesWatched"),
            @Mapping(source = "estado", target = "active")
    })
    Viewing toViewing(SuscripcionContenido suscripcionContenido);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "idVisualizacion", ignore = true),
            @Mapping(target = "suscripcion", ignore = true)
    })
    SuscripcionContenido toSuscripcionContenido(Viewing viewing);
}
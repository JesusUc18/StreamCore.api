package com.mx.edu.tecdesoftware.StreamCore.api.persistence.mapper;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Plan;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PlanMapper {

    @Mappings({
            @Mapping(source = "idPlan", target = "planId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "descripcion", target = "description"),
            @Mapping(source = "precioMensual", target = "monthlyPrice"),
            @Mapping(source = "pantallasSimultaneas", target = "simultaneousScreens"),
            @Mapping(source = "calidadVideo", target = "videoQuality"),
            @Mapping(source = "estado", target = "state")
    })
    Plan toPlan(com.mx.edu.tecdesoftware.StreamCore.api.persistence.entity.Plan plan);

    @InheritInverseConfiguration
    @Mapping(target = "suscripciones", ignore = true)
    com.mx.edu.tecdesoftware.StreamCore.api.persistence.entity.Plan toPlanEntity(Plan plan);
}
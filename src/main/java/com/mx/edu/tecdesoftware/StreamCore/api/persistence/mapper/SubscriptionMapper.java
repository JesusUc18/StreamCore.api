package com.mx.edu.tecdesoftware.StreamCore.api.persistence.mapper;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Subscription;
import com.mx.edu.tecdesoftware.StreamCore.api.persistence.entity.Suscripcion;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {ViewingMapper.class}
)
public interface SubscriptionMapper {

    @Mappings({
            @Mapping(source = "idSuscripcion", target = "subscriptionId"),
            @Mapping(source = "idUsuario", target = "userId"),
            @Mapping(source = "idPlan", target = "planId"),
            @Mapping(source = "fechaInicio", target = "startDate"),
            @Mapping(source = "fechaFin", target = "endDate"),
            @Mapping(source = "medioPago", target = "paymentMethod"),
            @Mapping(source = "comentario", target = "comment"),
            @Mapping(source = "estado", target = "state"),
            @Mapping(source = "visualizaciones", target = "viewings")
    })
    Subscription toSubscription(Suscripcion suscripcion);

    List<Subscription> toSubscriptions(List<Suscripcion> suscripciones);

    @InheritInverseConfiguration
    Suscripcion toSuscripcion(Subscription subscription);
}
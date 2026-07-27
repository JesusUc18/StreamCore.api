package com.mx.edu.tecdesoftware.StreamCore.api.persistence;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Subscription;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.repository.SubscriptionRepository;
import com.mx.edu.tecdesoftware.StreamCore.api.persistence.crud.SuscripcionCrudRepository;
import com.mx.edu.tecdesoftware.StreamCore.api.persistence.entity.Suscripcion;
import com.mx.edu.tecdesoftware.StreamCore.api.persistence.mapper.SubscriptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SuscripcionRepository implements SubscriptionRepository {

    @Autowired
    private SuscripcionCrudRepository suscripcionCrudRepository;

    @Autowired
    private SubscriptionMapper mapper;

    @Override
    public List<Subscription> getAll() {
        return mapper.toSubscriptions((List<Suscripcion>) suscripcionCrudRepository.findAll());
    }

    @Override
    public Optional<List<Subscription>> getByUser(String userId) {
        List<Suscripcion> suscripciones = suscripcionCrudRepository.findByIdUsuario(userId);
        return Optional.of(mapper.toSubscriptions(suscripciones));
    }

    @Override
    public Subscription save(Subscription subscription) {
        Suscripcion suscripcion = mapper.toSuscripcion(subscription);
        suscripcion.setIdSuscripcion(null);

        if (suscripcion.getVisualizaciones() != null) {
            suscripcion.getVisualizaciones().forEach(v -> v.setSuscripcion(suscripcion));
        }

        return mapper.toSubscription(suscripcionCrudRepository.save(suscripcion));
    }
}
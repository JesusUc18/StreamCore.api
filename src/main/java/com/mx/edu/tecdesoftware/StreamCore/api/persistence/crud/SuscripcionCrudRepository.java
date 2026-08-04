package com.mx.edu.tecdesoftware.StreamCore.api.persistence.crud;

import com.mx.edu.tecdesoftware.StreamCore.api.persistence.entity.Suscripcion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuscripcionCrudRepository extends CrudRepository<Suscripcion, Integer> {

    List<Suscripcion> findByIdUsuario(String idUsuario);

    boolean existsByIdPlan(Integer idPlan);
}
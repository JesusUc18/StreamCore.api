package com.mx.edu.tecdesoftware.StreamCore.api.persistence.crud;

import com.mx.edu.tecdesoftware.StreamCore.api.persistence.entity.Contenido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContenidoCrudRepository extends CrudRepository<Contenido, Integer> {
}
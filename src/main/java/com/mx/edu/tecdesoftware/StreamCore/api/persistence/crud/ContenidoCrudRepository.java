package com.mx.edu.tecdesoftware.StreamCore.api.persistence.crud;

import com.mx.edu.tecdesoftware.StreamCore.api.persistence.entity.Contenido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContenidoCrudRepository extends CrudRepository<Contenido, Integer> {

    List<Contenido> findByIdCategoriaOrderByTituloAsc(Integer idCategoria);

    Optional<List<Contenido>> findByTipoAndEstado(String tipo, Boolean estado);
}
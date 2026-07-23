package com.mx.edu.tecdesoftware.StreamCore.api.persistence.crud;

import com.mx.edu.tecdesoftware.StreamCore.api.persistence.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioCrudRepository extends CrudRepository<Usuario, String> {
}
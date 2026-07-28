package com.mx.edu.tecdesoftware.StreamCore.api.persistence.mapper;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.User;
import com.mx.edu.tecdesoftware.StreamCore.api.persistence.entity.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "id", target = "userId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "apellidos", target = "lastName"),
            @Mapping(source = "celular", target = "phone"),
            @Mapping(source = "direccion", target = "address"),
            @Mapping(source = "correoElectronico", target = "email")
    })
    User toUser(Usuario usuario);

    List<User> toUsers(List<Usuario> usuarios);

    @InheritInverseConfiguration
    @Mapping(target = "suscripciones", ignore = true)
    Usuario toUsuario(User user);
}
package com.mx.edu.tecdesoftware.StreamCore.api.persistence;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.User;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.repository.UserRepository;
import com.mx.edu.tecdesoftware.StreamCore.api.persistence.crud.UsuarioCrudRepository;
import com.mx.edu.tecdesoftware.StreamCore.api.persistence.entity.Usuario;
import com.mx.edu.tecdesoftware.StreamCore.api.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository implements UserRepository {

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    @Autowired
    private UserMapper mapper;

    @Override
    public List<User> getAll() {
        return mapper.toUsers((List<Usuario>) usuarioCrudRepository.findAll());
    }

    @Override
    public Optional<User> getUser(String userId) {
        return usuarioCrudRepository.findById(userId)
                .map(usuario -> mapper.toUser(usuario));
    }

    @Override
    public User save(User user) {
        Usuario usuario = mapper.toUsuario(user);
        return mapper.toUser(usuarioCrudRepository.save(usuario));
    }

    @Override
    public void delete(String userId) {
        usuarioCrudRepository.deleteById(userId);
    }

    @Override
    public Optional<User> update(String userId, User user) {
        return usuarioCrudRepository.findById(userId)
                .map(usuario -> {
                    usuario.setNombre(user.getName());
                    usuario.setApellidos(user.getLastName());
                    usuario.setCelular(user.getPhone());
                    usuario.setDireccion(user.getAddress());
                    usuario.setCorreoElectronico(user.getEmail());
                    return mapper.toUser(usuarioCrudRepository.save(usuario));
                });
    }
}
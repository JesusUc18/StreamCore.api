package com.mx.edu.tecdesoftware.StreamCore.api.domain.repository;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> getAll();
    Optional<User> getUser(String userId);
    User save(User user);
    void delete(String userId);
    Optional<User> update(String userId, User user);
}
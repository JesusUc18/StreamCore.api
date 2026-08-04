package com.mx.edu.tecdesoftware.StreamCore.api.domain.service;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.User;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.repository.SubscriptionRepository;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.repository.UserRepository;
import com.mx.edu.tecdesoftware.StreamCore.api.web.exception.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public Optional<User> getUser(String userId) {
        return userRepository.getUser(userId);
    }

    public User save(User user) {
        if (userRepository.existsById(user.getUserId())) {
            throw new ConflictException("Ya existe un usuario con el ID '" + user.getUserId() + "'.");
        }
        if (userRepository.getUserByEmail(user.getEmail()).isPresent()) {
            throw new ConflictException("Ya existe un usuario registrado con el correo '" + user.getEmail() + "'.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean delete(String userId) {
        return getUser(userId).map(user -> {
            boolean hasSubscriptions = subscriptionRepository.getByUser(userId)
                    .map(subs -> !subs.isEmpty())
                    .orElse(false);
            if (hasSubscriptions) {
                throw new ConflictException("No se puede eliminar: el usuario '" + userId + "' tiene suscripciones asociadas.");
            }
            userRepository.delete(userId);
            return true;
        }).orElse(false);
    }

    public Optional<User> update(String userId, User user) {
        return userRepository.update(userId, user);
    }
}
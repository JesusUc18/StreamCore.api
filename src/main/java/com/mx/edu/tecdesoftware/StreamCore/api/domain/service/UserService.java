package com.mx.edu.tecdesoftware.StreamCore.api.domain.service;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.User;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public Optional<User> getUser(String userId) {
        return userRepository.getUser(userId);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean delete(String userId) {
        return getUser(userId).map(user -> {
            userRepository.delete(userId);
            return true;
        }).orElse(false);
    }

    public Optional<User> update(String userId, User user) {
        return userRepository.update(userId, user);
    }
}
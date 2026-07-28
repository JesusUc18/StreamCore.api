package com.mx.edu.tecdesoftware.StreamCore.api.domain.service;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Subscription;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<Subscription> getAll() {
        return subscriptionRepository.getAll();
    }

    public Optional<List<Subscription>> getByUser(String userId) {
        return subscriptionRepository.getByUser(userId);
    }

    public Subscription save(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public Optional<Subscription> getSubscription(int subscriptionId) {
        return subscriptionRepository.getSubscription(subscriptionId);
    }

    public Optional<Subscription> updateState(int subscriptionId, String state) {
        return subscriptionRepository.updateState(subscriptionId, state);
    }
}
package com.mx.edu.tecdesoftware.StreamCore.api.domain.repository;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository {

    List<Subscription> getAll();
    Optional<List<Subscription>> getByUser(String userId);
    boolean existsByPlan(int planId);
    Subscription save(Subscription subscription);
    Optional<Subscription> getSubscription(int subscriptionId);
    Optional<Subscription> updateState(int subscriptionId, String state);
    void delete(int subscriptionId);
}
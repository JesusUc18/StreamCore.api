package com.mx.edu.tecdesoftware.StreamCore.api.domain.service;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Subscription;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.Viewing;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.repository.ContentRepository;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.repository.PlanRepository;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.repository.SubscriptionRepository;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.repository.UserRepository;
import com.mx.edu.tecdesoftware.StreamCore.api.web.exception.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private ContentRepository contentRepository;

    public List<Subscription> getAll() {
        return subscriptionRepository.getAll();
    }

    public Optional<List<Subscription>> getByUser(String userId) {
        return subscriptionRepository.getByUser(userId);
    }

    public Subscription save(Subscription subscription) {
        if (!userRepository.existsById(subscription.getUserId())) {
            throw new ConflictException("El usuario '" + subscription.getUserId() + "' indicado no existe.");
        }
        if (planRepository.getPlan(subscription.getPlanId()).isEmpty()) {
            throw new ConflictException("El plan '" + subscription.getPlanId() + "' indicado no existe.");
        }
        if (subscription.getViewings() != null) {
            for (Viewing viewing : subscription.getViewings()) {
                if (viewing.getContentId() == null || contentRepository.getContent(viewing.getContentId()).isEmpty()) {
                    throw new ConflictException("El contenido '" + viewing.getContentId() + "' de una visualización indicada no existe.");
                }
            }
        }
        return subscriptionRepository.save(subscription);
    }

    public Optional<Subscription> getSubscription(int subscriptionId) {
        return subscriptionRepository.getSubscription(subscriptionId);
    }

    public Optional<Subscription> updateState(int subscriptionId, String state) {
        return subscriptionRepository.updateState(subscriptionId, state);
    }

    public boolean delete(int subscriptionId) {
        return getSubscription(subscriptionId).map(subscription -> {
            subscriptionRepository.delete(subscriptionId);
            return true;
        }).orElse(false);
    }
}
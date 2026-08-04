package com.mx.edu.tecdesoftware.StreamCore.api.domain.service;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Plan;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.repository.PlanRepository;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.repository.SubscriptionRepository;
import com.mx.edu.tecdesoftware.StreamCore.api.web.exception.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<Plan> getAll() {
        return planRepository.getAll();
    }

    public Optional<Plan> getPlan(int planId) {
        return planRepository.getPlan(planId);
    }

    public Plan save(Plan plan) {
        return planRepository.save(plan);
    }

    public boolean delete(int planId) {
        return getPlan(planId).map(plan -> {
            if (subscriptionRepository.existsByPlan(planId)) {
                throw new ConflictException("No se puede eliminar: el plan '" + planId + "' tiene suscripciones asociadas.");
            }
            planRepository.delete(planId);
            return true;
        }).orElse(false);
    }
}
package com.mx.edu.tecdesoftware.StreamCore.api.domain.service;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Plan;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

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
            planRepository.delete(planId);
            return true;
        }).orElse(false);
    }
}
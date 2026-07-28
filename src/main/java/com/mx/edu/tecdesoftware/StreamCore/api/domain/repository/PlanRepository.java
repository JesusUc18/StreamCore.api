package com.mx.edu.tecdesoftware.StreamCore.api.domain.repository;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Plan;

import java.util.List;
import java.util.Optional;

public interface PlanRepository {

    List<Plan> getAll();
    Optional<Plan> getPlan(int planId);
    Plan save(Plan plan);
    void delete(int planId);
}
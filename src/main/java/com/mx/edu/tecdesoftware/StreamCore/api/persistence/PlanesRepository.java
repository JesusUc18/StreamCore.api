package com.mx.edu.tecdesoftware.StreamCore.api.persistence;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Plan;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.repository.PlanRepository;
import com.mx.edu.tecdesoftware.StreamCore.api.persistence.crud.PlanCrudRepository;
import com.mx.edu.tecdesoftware.StreamCore.api.persistence.mapper.PlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class PlanesRepository implements PlanRepository {

    @Autowired
    private PlanCrudRepository planCrudRepository;

    @Autowired
    private PlanMapper mapper;

    @Override
    public List<Plan> getAll() {
        return StreamSupport.stream(planCrudRepository.findAll().spliterator(), false)
                .map(mapper::toPlan)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Plan> getPlan(int planId) {
        return planCrudRepository.findById(planId)
                .map(mapper::toPlan);
    }

    @Override
    public Plan save(Plan plan) {
        com.mx.edu.tecdesoftware.StreamCore.api.persistence.entity.Plan entity = mapper.toPlanEntity(plan);
        entity.setIdPlan(null);
        return mapper.toPlan(planCrudRepository.save(entity));
    }

    @Override
    public void delete(int planId) {
        planCrudRepository.deleteById(planId);
    }
}
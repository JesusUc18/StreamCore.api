package com.mx.edu.tecdesoftware.StreamCore.api.persistence.crud;

import com.mx.edu.tecdesoftware.StreamCore.api.persistence.entity.Plan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanCrudRepository extends CrudRepository<Plan, Integer> {
}
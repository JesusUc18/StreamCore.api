package com.mx.edu.tecdesoftware.StreamCore.api.web.controller;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Plan;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
public class PlanController {

    @Autowired
    private PlanService planService;

    @GetMapping("")
    public ResponseEntity<List<Plan>> getAll() {
        return ResponseEntity.ok(planService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plan> getPlan(@PathVariable("id") int planId) {
        return planService.getPlan(planId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Plan> save(@RequestBody Plan plan) {
        return ResponseEntity.status(HttpStatus.CREATED).body(planService.save(plan));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int planId) {
        if (planService.delete(planId)) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }
}
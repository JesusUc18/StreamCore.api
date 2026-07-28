package com.mx.edu.tecdesoftware.StreamCore.api.web.controller;

import com.mx.edu.tecdesoftware.StreamCore.api.domain.Subscription;
import com.mx.edu.tecdesoftware.StreamCore.api.domain.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/all")
    public ResponseEntity<List<Subscription>> getAll() {
        return new ResponseEntity<>(subscriptionService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Subscription>> getByUser(@PathVariable("id") String userId) {
        return subscriptionService.getByUser(userId)
                .map(subscriptions -> new ResponseEntity<>(subscriptions, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Subscription> save(@RequestBody Subscription subscription) {
        return new ResponseEntity<>(subscriptionService.save(subscription), HttpStatus.CREATED);
    }
}
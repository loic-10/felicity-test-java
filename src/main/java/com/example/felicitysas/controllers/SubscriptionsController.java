package com.example.felicitysas.controllers;

import com.example.felicitysas.exceptions.ResourceNotFoundException;
import com.example.felicitysas.models.Subscription;
import com.example.felicitysas.repositories.SubscriptionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionsController {

    final SubscriptionRepository subscriptionRepository;

    public SubscriptionsController(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @GetMapping(name = "/")
    public ResponseEntity<List<Subscription>> getAll() {

        List<Subscription> subscriptions = new ArrayList<>(subscriptionRepository.findAll());
        if (subscriptions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getOne(@PathVariable("id") long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Subscription with id = " + id));
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Subscription> create(@RequestBody Subscription _subscription) {
        _subscription.setDate(new Date());
        Subscription subscription = subscriptionRepository.save(_subscription);
        return new ResponseEntity<>(subscription, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Subscription> update(@PathVariable("id") long id, @RequestBody Subscription _subscription) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Subscription with id = " + id));

        subscription.setDate(_subscription.getDate());
        subscription.setPeriod(_subscription.getPeriod());
        subscription.setTotalPrice(_subscription.getTotalPrice());
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Subscription> delete(@PathVariable("id") long id) {
        subscriptionRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

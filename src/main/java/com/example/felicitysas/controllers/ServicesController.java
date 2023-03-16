package com.example.felicitysas.controllers;

import com.example.felicitysas.exceptions.ResourceNotFoundException;
import com.example.felicitysas.models.Service;
import com.example.felicitysas.models.Subscription;
import com.example.felicitysas.repositories.ServiceRepository;
import com.example.felicitysas.repositories.SubscriptionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServicesController {

    final ServiceRepository serviceRepository;
    final SubscriptionRepository subscriptionRepository;

    public ServicesController(ServiceRepository serviceRepository, SubscriptionRepository subscriptionRepository) {
        this.serviceRepository = serviceRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @GetMapping(name = "/")
    public ResponseEntity<List<Service>> getAll() {
        List<Service> services = new ArrayList<>();

        boolean isUser = false;

        if (isUser)
            services.addAll(serviceRepository.findAll());
        else
            services.addAll(serviceRepository.findByAvailableIsTrue());

        if (services.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> getOne(@PathVariable("id") long id) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Service with id = " + id));
        return new ResponseEntity<>(service, HttpStatus.OK);
    }

    @PostMapping(name = "/")
    public ResponseEntity<Service> create(@RequestBody Service _service) {
        Service service = serviceRepository.save(new Service(_service.getName(), _service.getDescription(), _service.getPrice(), _service.getAvailable()));
        return new ResponseEntity<>(service, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Service> update(@PathVariable("id") long id, @RequestBody Service _service) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Service with id = " + id));

        service.setName(_service.getName());
        service.setDescription(_service.getDescription());
        service.setPrice(_service.getPrice());
        service.setAvailable(_service.getAvailable());
        return new ResponseEntity<>(service, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Service> delete(@PathVariable("id") long id) {
        serviceRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/subscriptions")
    public ResponseEntity<List<Subscription>> getSubscriptions(@PathVariable("id") long id) {
        List<Subscription> subscriptions = new ArrayList<>(subscriptionRepository.findByService(new Service(id)));

        if (subscriptions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }
}

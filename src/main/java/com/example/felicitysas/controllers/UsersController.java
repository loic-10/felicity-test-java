package com.example.felicitysas.controllers;

import com.example.felicitysas.exceptions.ResourceNotFoundException;
import com.example.felicitysas.models.Subscription;
import com.example.felicitysas.models.User;
import com.example.felicitysas.repositories.SubscriptionRepository;
import com.example.felicitysas.repositories.UserRepository;
import com.example.felicitysas.responses.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    final UserRepository userRepository;
    final SubscriptionRepository subscriptionRepository;

    public UsersController(UserRepository userRepository, SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @GetMapping(name = "/")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = new ArrayList<>(userRepository.findAll());

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOne(@PathVariable("id") long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<User> create(@RequestBody User _user) {
        User user = userRepository.save(_user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User _user) {
        User user = userRepository.save(new User(_user.getUsername(), _user.getFullName(), _user.getPassword(), _user.getRole()));
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User _user) {
        User user = userRepository.save(_user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") long id, @RequestBody User _user) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + id));

        user.setPassword(_user.getPassword());
        user.setFullName(_user.getFullName());
        user.setUsername(_user.getUsername());
        user.setRole(_user.getRole());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage<User>> delete(@PathVariable("id") long id) {
        userRepository.deleteById(id);
        ResponseMessage<User> userResponseMessage = new ResponseMessage<>("User " + id + " updated successfully!");
        return new ResponseEntity<>(userResponseMessage, HttpStatus.OK);
    }

    @GetMapping("/{id}/subscriptions")
    public ResponseEntity<List<Subscription>> getSubscriptions(@PathVariable("id") long id) {
        List<Subscription> subscriptions = new ArrayList<>(subscriptionRepository.findByUser(new User(id)));

        if (subscriptions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }
}

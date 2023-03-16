package com.example.felicitysas.repositories;

import com.example.felicitysas.models.Service;
import com.example.felicitysas.models.Subscription;
import com.example.felicitysas.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findAll();
    List<Subscription> findByService(Service service);
    List<Subscription> findByUser(User user);
}

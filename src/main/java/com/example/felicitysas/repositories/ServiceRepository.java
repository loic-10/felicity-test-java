package com.example.felicitysas.repositories;

import com.example.felicitysas.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  ServiceRepository extends JpaRepository<Service, Long> {
    public List<Service> findByAvailableIsTrue();
}

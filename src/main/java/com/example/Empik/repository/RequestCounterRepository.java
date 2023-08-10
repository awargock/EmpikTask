package com.example.Empik.repository;

import com.example.Empik.model.RequestCounter;
import org.springframework.data.repository.CrudRepository;

public interface RequestCounterRepository extends CrudRepository<RequestCounter, String> {
    RequestCounter findByLogin(String login);
}


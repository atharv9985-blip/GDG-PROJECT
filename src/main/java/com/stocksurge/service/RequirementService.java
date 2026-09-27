package com.stocksurge.service;

import com.stocksurge.entity.Requirement;
import com.stocksurge.entity.User;
import com.stocksurge.repository.RequirementRepository;
import com.stocksurge.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequirementService {

    private final RequirementRepository repository;
    private final UserRepository userRepository;

    public RequirementService(
            RequirementRepository repository,
            UserRepository userRepository) {

        this.repository = repository;
        this.userRepository = userRepository;
    }

    public Requirement create(Requirement x, Long buyerId) {

        User buyer = userRepository.findById(buyerId)
                .orElseThrow(() -> new RuntimeException("Buyer not found: " + buyerId));

        x.setBuyer(buyer);

        return repository.save(x);
    }

    public List<Requirement> getAll() {
        return repository.findAll();
    }

    public Requirement getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Requirement not found: " + id));
    }
}